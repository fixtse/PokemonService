
package pe.edu.ulima.ulpokemonapi.ulpokemonapi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.usuario.UsuarioResponse;

public class UsuarioDAO {
    public Connection conectarse() throws ClassNotFoundException, SQLException{
        Class.forName(Parametros.DRIVER_CLASS);
        return DriverManager.getConnection(Parametros.URL
                + "?user=" + Parametros.USER + "&password=" + Parametros.PASSWORD);
    }
    
    public void desconectarse(Connection conn) throws SQLException{
        conn.close();
    }
    
    public UsuarioResponse obtener(Connection conn, String usuario, String password) throws SQLException{
        String sql = "SELECT * FROM usuario WHERE username=? and password=?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1, usuario);
        ps.setString(2, password);
        
        ResultSet rs = ps.executeQuery();
        
        UsuarioResponse user = null;
        if(rs.next()){
            user = new UsuarioResponse(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password")
            );
        }
        
        return user;
        
    }
    
    public void agregar(Connection conn, Usuario usuario) throws SQLException{
        String sql = "INSERT INTO usuario (username, password) VALUES (?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1, usuario.getUsername());
        ps.setString(2, usuario.getPassword());
        
        ps.execute();
    }
}
