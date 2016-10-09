

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.GeneralResponse;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.IPokeAPIClient;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.JsonTransformer;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.usuario.LoginRequest;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.usuario.LoginResponse;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.pokemon.PokeAPIResponse;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.pokemon.PokemonResponse;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.usuario.RegistroRequest;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.ServiceGenerator;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.characteristic.CharacteristicResponse;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.characteristic.Description;
//import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.pokemon.Type;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.usuario.Status;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.usuario.UsuarioResponse;
//import pe.edu.ulima.ulpokemonapi.ulpokemonapi.model.Pokemon;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.model.PokemonDAO;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.model.UsuarioDAO;
import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.post;
//import static spark.Spark.stop;

public class Main {

    public static void main(String[] args) {
        port(Integer.parseInt(System.getenv("PORT")));
        //port(4567);

        // Endpoint para realizar un login
        post("/usuarios/login", (req, resp) -> {
            String data = req.body();

            LoginRequest request = new Gson().fromJson(data, LoginRequest.class);

            UsuarioDAO usuarioDAO = new UsuarioDAO();

            Connection conn = usuarioDAO.conectarse();
            UsuarioResponse usuario = usuarioDAO.obtener(conn, request.getUser(), request.getPassword());

            usuarioDAO.desconectarse(conn);

            if (usuario == null) {
                return new LoginResponse("Error en login", new Status(0, ""), usuario);
            } else {
                return new LoginResponse("", new Status(0, ""), usuario);
            }
        }, new JsonTransformer());

        // Endpoint para registrar un nuevo usuario
        post("/usuarios", (req, resp) -> {
            String data = req.body();

            RegistroRequest request = new Gson().fromJson(data, RegistroRequest.class);

            UsuarioDAO usuarioDAO = new UsuarioDAO();

            Connection conn = null;
            try {
                conn = usuarioDAO.conectarse();
                usuarioDAO.agregar(conn, request.getUsuario());
            } catch (SQLException | ClassNotFoundException ex) {
                return new GeneralResponse(new Status(1, "Error SQL: " + ex.getMessage()));
            } finally {
                if (conn != null) {
                    usuarioDAO.desconectarse(conn);
                }
            }

            return new GeneralResponse(new Status(0, ""));

        }, new JsonTransformer());

        // Endpoint para obtener el listado de pokemones por usuario
        get("/usuarios/:usuarioid/pokemones", (req, resp) -> {
            int usuarioId = Integer.valueOf(req.params("usuarioid"));

            PokemonDAO pokemonDAO = new PokemonDAO();

            Connection conn = null;

            List<Integer> pokemones;
            try {
                conn = pokemonDAO.conectarse();
                pokemones = pokemonDAO.listar(conn, usuarioId);
            } catch (SQLException | ClassNotFoundException ex) {
                return new GeneralResponse(new Status(1, "Error SQL: " + ex.getMessage()));
            } finally {
                if (conn != null) {
                    pokemonDAO.desconectarse(conn);
                }
            }

            return pokemones;
        }, new JsonTransformer());

        // Endpoint para obtener datos del pokemon
        get("/pokemones/:pokemonid", (req, resp) -> {
            int pokemonId = Integer.valueOf(req.params("pokemonid"));

            PokemonResponse pokemonResponse = new PokemonResponse();
            
            IPokeAPIClient client = ServiceGenerator.createService(IPokeAPIClient.class);
            
            // Obtener datos del pokemon
            Call<PokeAPIResponse> datosCall = client.obtenerPokemon(pokemonId);
            PokeAPIResponse pokeApiResponse = datosCall.execute().body();
            pokemonResponse.setNombre(pokeApiResponse.getName());
            pokemonResponse.setNivel(pokeApiResponse.getWeight());
            String tipos = "";
            for (int i = 0; i < pokeApiResponse.getTypes().size(); i++) {
                tipos = tipos + pokeApiResponse.getTypes().get(i);
                if (i + 1 != pokeApiResponse.getTypes().size()) {
                    tipos = tipos + ", ";
                }
            }
            pokemonResponse.setTipo(tipos);
            pokemonResponse.setUrl(pokeApiResponse.getSprites().getUrl());
            
            //Obtener descripción del pokemon
            Call<CharacteristicResponse> descripcionCall = client.obtenerDescripcion(pokemonId);
            CharacteristicResponse characteristicResponse = descripcionCall.execute().body();
            
            for (Description descripcion : characteristicResponse.getDescriptions()) {
                if (descripcion.getLanguage().getName().equals("en")) {
                    pokemonResponse.setDescripcion(descripcion.getDescription());
                    break;
                }
            }
            
            if (pokemonResponse.getDescripcion().length() == 0) {
                pokemonResponse.setDescripcion("No description available.");
            }

            return pokemonResponse;
        }, new JsonTransformer());

        // Endpoint para obtener el listado de pokemones por usuario
        get("/disponibles", (req, resp) -> {
            Calendar ahora = Calendar.getInstance();
            ahora.add(Calendar.HOUR_OF_DAY, -5); // No funcionó cambiar el timezone
            int horas = ahora.get(Calendar.HOUR_OF_DAY);
            int minutos = ahora.get(Calendar.MINUTE);
            int minuto = (horas * 60) + minutos;

            PokemonDAO pokemonDAO = new PokemonDAO();

            Connection conn = null;

            List<Integer> pokemones;
            try {
                conn = pokemonDAO.conectarse();
                pokemones = pokemonDAO.obtenerDisponibles(conn, minuto);
                
            } catch (SQLException | ClassNotFoundException ex) {
                return new GeneralResponse(new Status(1, "Error SQL: " + ex.getMessage()));
            } finally {
                if (conn != null) {
                    pokemonDAO.desconectarse(conn);
                }
            }

            return pokemones;
        }, new JsonTransformer());

        // Endpoint para registrar pokemones de un usuario
        post("/pokemones/:usuarioid/:pokemonid", (req, resp) -> {
            int usuarioId = Integer.valueOf(req.params("usuarioid"));
            int pokemonId = Integer.valueOf(req.params("pokemonid"));

            PokemonDAO pokemonDAO = new PokemonDAO();

            Connection conn = null;

            try {
                conn = pokemonDAO.conectarse();
                pokemonDAO.capturarPokemon(conn, usuarioId, pokemonId);
            } catch (SQLException | ClassNotFoundException ex) {
                return new GeneralResponse(new Status(1, "Error SQL: " + ex.getMessage()));
            } finally {
                if (conn != null) {
                    pokemonDAO.desconectarse(conn);
                }
            }

            return new GeneralResponse(new Status(0, ""));

        }, new JsonTransformer());

        /*
        // Endpoint para parar el servidor
        get("/parar", (req, resp)->{
            stop();
            return "";
        });
         */
    }
}
