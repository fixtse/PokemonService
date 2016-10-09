package pe.edu.ulima.ulpokemonapi.ulpokemonapi;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.GeneralResponse;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.JsonTransformer;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.LoginRequest;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.LoginResponse;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.RegistroRequest;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.Status;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.UsuarioResponse;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.model.Pokemon;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.model.PokemonDAO;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.model.UsuarioDAO;
import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.post;
//import static spark.Spark.stop;

public class Main {

    public static void main(String[] args) {
        port(Integer.parseInt(System.getenv("PORT")));

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
        get("/usuarios/:usuarioId/pokemones", (req, resp) -> {
            String usuarioId = req.params("usuarioId");

            PokemonDAO pokemonDAO = new PokemonDAO();

            Connection conn = null;

            List<Pokemon> pokemones;
            try {
                conn = pokemonDAO.conectarse();
                pokemones = pokemonDAO.listar(conn);
            } catch (SQLException | ClassNotFoundException ex) {
                return new GeneralResponse(new Status(1, "Error SQL: " + ex.getMessage()));
            } finally {
                if (conn != null) {
                    pokemonDAO.desconectarse(conn);
                }
            }

            return pokemones;
        }, new JsonTransformer());

        // Endpoint para obtener el listado de pokemones por usuario
        get("/pokemones/disponibles", (req, resp) -> {
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
