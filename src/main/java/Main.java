
import com.google.gson.Gson;

import pe.edu.mongo.UsuarioDAO;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.usuario.LoginRequest;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.usuario.NumReq;
import static spark.Spark.port;
import static spark.Spark.post;

public class Main {

    public static void main(String[] args) {
        port(Integer.parseInt(System.getenv("PORT")));
        //port(4567);

        //IPokeAPIClient client = ServiceGenerator.createService(IPokeAPIClient.class);
        
        //Endpoint para sumar
        post("/usuario", (req, resp) -> {
            
            
            UsuarioDAO user = new UsuarioDAO();
            LoginRequest log = new Gson().fromJson(req.body(), LoginRequest.class);            
            if (user.validarUsuario(log.getUser(), log.getPassword())){
                int tipo = user.getTipo(log.getUser());
                return tipo;
            }else{
                return 0;
            }         
            
            
            
        });
        
        post("/verSum", (req, resp) -> {
            
            
            UsuarioDAO user = new UsuarioDAO();
            LoginRequest log = new Gson().fromJson(req.body(), LoginRequest.class);            
            if (user.validarUsuario(log.getUser(), log.getPassword())){
                int tipo = user.getTipo(log.getUser());
                return tipo;
            }else{
                return 0;
            }         
            
            
            
        });
        
        post("/resta", (req, resp) -> {
            
            NumReq numeros = new Gson().fromJson(req.body(), NumReq.class);
            
            
            return numeros.getNum1()
                   -numeros.getNum2();
        });
        
        post("/multi", (req, resp) -> {
            
            NumReq numeros = new Gson().fromJson(req.body(), NumReq.class);
            
            
            return numeros.getNum1()
                   *numeros.getNum2();
        });
        
        post("/divi", (req, resp) -> {
            
            NumReq numeros = new Gson().fromJson(req.body(), NumReq.class);
            
            
            return (numeros.getNum1()
                   /numeros.getNum2())*2;
        });
        

        // Endpoint para realizar un login
//        post("/usuarios/login", (req, resp) -> {
//            String data = req.body();
//
//            LoginRequest request = new Gson().fromJson(data, LoginRequest.class);
//
//            UsuarioDAO usuarioDAO = new UsuarioDAO();
//
//            Connection conn = usuarioDAO.conectarse();
//            UsuarioResponse usuario = usuarioDAO.obtener(conn, request.getUser(), request.getPassword());
//
//            usuarioDAO.desconectarse(conn);
//
//            if (usuario == null) {
//                return new LoginResponse("Error en login", new Status(0, ""), usuario);
//            } else {
//                return new LoginResponse("", new Status(0, ""), usuario);
//            }
//        }, new JsonTransformer());
//
//        // Endpoint para registrar un nuevo usuario
//        post("/usuarios", (req, resp) -> {
//            String data = req.body();
//
//            RegistroRequest request = new Gson().fromJson(data, RegistroRequest.class);
//
//            UsuarioDAO usuarioDAO = new UsuarioDAO();
//
//            Connection conn = null;
//            try {
//                conn = usuarioDAO.conectarse();
//                usuarioDAO.agregar(conn, request.getUsuario());
//            } catch (SQLException | ClassNotFoundException ex) {
//                return new GeneralResponse(new Status(1, "Error SQL: " + ex.getMessage()));
//            } finally {
//                if (conn != null) {
//                    usuarioDAO.desconectarse(conn);
//                }
//            }
//
//            return new GeneralResponse(new Status(0, ""));
//
//        }, new JsonTransformer());
//
//        // Endpoint para obtener el listado de pokemones por usuario
//        get("/usuarios/:usuarioid/pokemones", (req, resp) -> {
//            int usuarioId = Integer.valueOf(req.params("usuarioid"));
//
//            PokemonDAO pokemonDAO = new PokemonDAO();
//
//            Connection conn = null;
//
//            List<Pokemon> pokemones = new ArrayList<>();
//            try {
//                conn = pokemonDAO.conectarse();
//
//                // Se obtiene la lista de ids de pokemones que ha capturado el usuario
//                List<Integer> pokemonesId = pokemonDAO.listar(conn, usuarioId);
//
//                if (pokemonesId != null && !pokemonesId.isEmpty()) {
//                    // Se obtienen los datos de pokemon según cada id de la lista pokemonIds
//                    Pokemon pokemon;
//
//                    Call<PokemonResponse> datosCall;
//                    PokemonResponse pokeApiResponse;
//                    String tipos;
//
//                    Call<CharacteristicResponse> descripcionCall;
//                    CharacteristicResponse characteristicResponse;
//                    for (Integer pokemonId : pokemonesId) {
//                        pokemon = new Pokemon();
//                        // Obtener datos del pokemon
//                        datosCall = client.obtenerPokemon(pokemonId);
//                        pokeApiResponse = datosCall.execute().body();
//                        pokemon.setNombre(Character.toUpperCase(pokeApiResponse.getNombre().charAt(0)) + pokeApiResponse.getNombre().substring(1));
//                        pokemon.setNivel(pokeApiResponse.getPeso());
//                        tipos = "";
//                        for (int i = 0; i < pokeApiResponse.getElementosTipo().size(); i++) {
//                            tipos = tipos + pokeApiResponse.getElementosTipo().get(i).getType().getName();
//                            if (i + 1 != pokeApiResponse.getElementosTipo().size()) {
//                                tipos = tipos + ", ";
//                            }
//                        }
//                        pokemon.setTipo(tipos);
//                        pokemon.setUrl(pokeApiResponse.getImagenUrl());
//
//                        //Obtener descripción del pokemon
//                        descripcionCall = client.obtenerDescripcion(pokemonId);
//                        characteristicResponse = descripcionCall.execute().body();
//
//                        if (characteristicResponse != null && !characteristicResponse.getDescriptions().isEmpty()) {
//                            for (Description descripcion : characteristicResponse.getDescriptions()) {
//                                if (descripcion.getLanguage().getName().equals("en")) {
//                                    pokemon.setDescripcion(descripcion.getDescription());
//                                    break;
//                                }
//                            }
//                        } else {
//                            pokemon.setDescripcion("No description available");
//                        }
//
//                        pokemones.add(pokemon);
//                    }
//                }
//            } catch (SQLException | ClassNotFoundException ex) {
//                return new GeneralResponse(new Status(1, "Error SQL: " + ex.getMessage()));
//            } finally {
//                if (conn != null) {
//                    pokemonDAO.desconectarse(conn);
//                }
//            }
//
//            return pokemones;
//        }, new JsonTransformer());
//
//        // Ya no se usa:
//        /*
//        // Endpoint para obtener datos del pokemon
//        get("/pokemones/:pokemonid", (req, resp) -> {
//            int pokemonId = Integer.valueOf(req.params("pokemonid"));
//
//            Pokemon pokemon = new Pokemon();
//
//            IPokeAPIClient client = ServiceGenerator.createService(IPokeAPIClient.class);
//
//            // Obtener datos del pokemon
//            Callback<PokeAPIResponse> pokeAPIResponseCallback = new Callback<PokeAPIResponse>() {
//                @Override
//                public void onResponse(Call<PokeAPIResponse> call, Response<PokeAPIResponse> rspns) {
//                    if (rspns.isSuccessful()) {
//                        PokeAPIResponse pokeAPIResponse = rspns.body();
//                        pokemon.setNombre(Character.toUpperCase(pokeAPIResponse.getName().charAt(0)) + pokeAPIResponse.getName().substring(1));
//                        pokemon.setNivel(pokeAPIResponse.getWeight());
//                        String tipos = "";
//                        for (int i = 0; i < pokeAPIResponse.getTypes().size(); i++) {
//                            tipos = tipos + pokeAPIResponse.getTypes().get(i).getType().getName();
//                            if (i + 1 != pokeAPIResponse.getTypes().size()) {
//                                tipos = tipos + ", ";
//                            }
//                        }
//                        pokemon.setTipo(tipos);
//                        pokemon.setUrl(pokeAPIResponse.getSprites().getUrl());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<PokeAPIResponse> call, Throwable thrwbl) {
//                    error();
//                }
//
//                public void error() {
//                    pokemon.setNombre("Error");
//                    pokemon.setNivel(-1);
//                    pokemon.setTipo("Error");
//                    pokemon.setUrl("http://www.boldlist.net/img/pokemon-go-dead-bodies.jpg");
//                    this.notify();
//                }
//            };
//            client.obtenerPokemon(pokemonId).enqueue(pokeAPIResponseCallback);
//
//            //Obtener descripción del pokemon
//            Callback<CharacteristicResponse> characteristicResponseCallback = new Callback<CharacteristicResponse>() {
//                @Override
//                public void onResponse(Call<CharacteristicResponse> call, Response<CharacteristicResponse> rspns) {
//                    if (rspns.isSuccessful()) {
//                        CharacteristicResponse characteristicResponse = rspns.body();
//                        for (Description descripcion : characteristicResponse.getDescriptions()) {
//                            if (descripcion.getLanguage().getName().equals("en")) {
//                                pokemon.setDescripcion(descripcion.getDescription());
//                                break;
//                            }
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<CharacteristicResponse> call, Throwable thrwbl) {
//                    pokemon.setDescripcion("No description available.");
//                }
//            };
//            client.obtenerDescripcion(pokemonId).enqueue(characteristicResponseCallback);
//
//
//            return pokemon;
//        }, new JsonTransformer()); 
//         */
//        // Endpoint para obtener el listado de pokemones por usuario
//        get("/disponibles", (req, resp) -> {
//            Calendar ahora = Calendar.getInstance();
//            // El timezone se configuró como config variable en heroku
//            int horas = ahora.get(Calendar.HOUR_OF_DAY);
//            int minutos = ahora.get(Calendar.MINUTE);
//            int minuto = (horas * 60) + minutos;
//
//            PokemonDAO pokemonDAO = new PokemonDAO();
//
//            Connection conn = null;
//
//            List<PokemonDisponible> pokemonesDisponibles = new ArrayList<>();
//            List<Integer> pokemonesId;
//            try {
//                conn = pokemonDAO.conectarse();
//                pokemonesId = pokemonDAO.obtenerDisponibles(conn, minuto);
//
//                if (pokemonesId != null && !pokemonesId.isEmpty()) {
//                    String pokemonNombre;
//                    String pokemonImagenUrl;
//
//                    Call<PokemonDisponibleResponse> pokemonesDisponiblesCall;
//                    PokemonDisponibleResponse pokemonDisponibleResponse;
//                    for (Integer pokemonId : pokemonesId) {
//                        pokemonesDisponiblesCall = client.obtenerPokemonDisponible(pokemonId);
//                        pokemonDisponibleResponse = pokemonesDisponiblesCall.execute().body();
//
//                        pokemonNombre = Character.toUpperCase(pokemonDisponibleResponse.getNombre().charAt(0)) + pokemonDisponibleResponse.getNombre().substring(1);
//                        pokemonImagenUrl = pokemonDisponibleResponse.getImagenUrl();
//
//                        pokemonesDisponibles.add(new PokemonDisponible(pokemonId, pokemonNombre, pokemonImagenUrl));
//                    }
//                }
//            } catch (SQLException | ClassNotFoundException ex) {
//                return new GeneralResponse(new Status(1, "Error SQL: " + ex.getMessage()));
//            } finally {
//                if (conn != null) {
//                    pokemonDAO.desconectarse(conn);
//                }
//            }
//
//            return pokemonesDisponibles;
//        }, new JsonTransformer());
//
//        // Endpoint para registrar pokemones de un usuario
//        post("/pokemones/atrapar", (req, resp) -> {
//            String data = req.body();
//
//            AtraparRequest request = new Gson().fromJson(data, AtraparRequest.class);
//
//            PokemonDAO pokemonDAO = new PokemonDAO();
//
//            Connection conn = null;
//
//            try {
//                conn = pokemonDAO.conectarse();
//                pokemonDAO.capturarPokemon(conn, request.getPokemonid(), request.getUsuarioid());
//            } catch (SQLException | ClassNotFoundException ex) {
//                return new GeneralResponse(new Status(1, "Error SQL: " + ex.getMessage()));
//            } finally {
//                if (conn != null) {
//                    pokemonDAO.desconectarse(conn);
//                }
//            }
//
//            return new GeneralResponse(new Status(0, ""));
//
//        }, new JsonTransformer());
//
//        /*
//        // Endpoint para parar el servidor
//        get("/parar", (req, resp)->{
//            stop();
//            return "";
//        });
//         */
    }
}
