
package pe.edu.ulima.ulpokemonapi.ulpokemonapi;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.GeneralResponse;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.JsonTransformer;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.LoginRequest;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.LoginResponse;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.RegistroRequest;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.Status;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.model.Pokemon;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.model.PokemonDAO;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.model.Usuario;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.model.UsuarioDAO;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.stop;

public class Main {
    public static void main(String[] args){
        post("/usuarios/login", (req, resp)->{
            String data = req.body();
            
            LoginRequest request = 
                    new Gson().fromJson(data, LoginRequest.class);
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            
            Connection conn = usuarioDAO.conectarse();
            Usuario usuario = 
                    usuarioDAO.obtener(conn, 
                            request.getUser(), 
                            request.getPassword());
            
            usuarioDAO.desconectarse(conn);
            
            if (usuario == null){
                return new LoginResponse("Error en login", new Status(0, ""));
            }else{
                return new LoginResponse("", new Status(0, ""));
            }
        }, new JsonTransformer());
        
        // Endpoint para registrar un nuevo usuario
        post("/usuarios", (req, resp)->{
            String data = req.body();
            
            RegistroRequest request =
                    new Gson().fromJson(data, RegistroRequest.class);
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            
            try {
                Connection conn = usuarioDAO.conectarse();
                usuarioDAO.agregar(conn, request.getUsuario());
            } catch (SQLException | ClassNotFoundException ex) {
                
                return new GeneralResponse(new Status(1, "Error SQL: " + 
                        ex.getMessage()));
            }
                    
            return new GeneralResponse(new Status(0, ""));
            
        }, new JsonTransformer());
        
        // Endpoint para obtener el listado de pokemones por usuario
        get("/usuarios/:usuarioId/pokemones", (req, resp)->{
            String usuarioId = req.params("usuarioId");
            
            PokemonDAO pokemonDAO = new PokemonDAO();
            try{
                Connection conn = pokemonDAO.conectarse();
                List<Pokemon> pokemones = 
                        pokemonDAO.listar(conn, Long.parseLong(usuarioId));
                return pokemones;
            } catch (SQLException | ClassNotFoundException ex) {
                
                return new GeneralResponse(new Status(1, "Error SQL: " + 
                        ex.getMessage()));
            }
            
        }, new JsonTransformer());
        
        // Endpoint para parar el servidor
        get("/parar", (req, resp)->{
            stop();
            return "";
        });
    }
}
