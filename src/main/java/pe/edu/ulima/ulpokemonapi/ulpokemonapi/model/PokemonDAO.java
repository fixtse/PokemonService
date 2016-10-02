
package pe.edu.ulima.ulpokemonapi.ulpokemonapi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAO {
    public Connection conectarse() throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(
                Parametros.URL, 
                Parametros.USER, 
                Parametros.PASSWORD);
        
    }
    
    public void desconectarse(Connection conn) throws SQLException{
        conn.close();
    }
    
    public List<Pokemon> listar(Connection conn) throws SQLException{
        String sql = "SELECT id, url, nombre, nivel, tipo, descripcion FROM pokemon";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        List<Pokemon> pokemones = new ArrayList<>();
        while(rs.next()){
            pokemones.add(new Pokemon(
                    rs.getLong("id"),
                    rs.getString("url"),
                    rs.getString("nombre"),
                    rs.getString("tipo"),
                    rs.getInt("nivel"),
                    rs.getString("descripcion")
            ));
        }
        
        return pokemones;
    }
    
    public List<Pokemon> listar(Connection conn, long idUsuario) throws SQLException{
        String sql = "SELECT p.id as id, p.url as url ,p.nombre as nombre, "
                + "p.nivel as nivel, p.tipo as tipo, p.descripcion as descripcion "
                + "FROM usuario_pokemon up INNER JOIN pokemon p ON (up.pokemonid=p.id) "
                + "WHERE up.usuarioid=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setLong(1, idUsuario);
        
        ResultSet rs = ps.executeQuery();
        
        List<Pokemon> pokemones = new ArrayList<>();
        while(rs.next()){
            pokemones.add(new Pokemon(
                    rs.getLong("id"),
                    rs.getString("url"),
                    rs.getString("nombre"),
                    rs.getString("tipo"),
                    rs.getInt("nivel"),
                    rs.getString("descripcion")
            ));
        }
        
        return pokemones;
    }
}
