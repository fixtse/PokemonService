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

    public Connection conectarse() throws ClassNotFoundException, SQLException {
        Class.forName(Parametros.DRIVER_CLASS);
        return DriverManager.getConnection(Parametros.URL
                + "?user=" + Parametros.USER + "&password=" + Parametros.PASSWORD);
    }

    public void desconectarse(Connection conn) throws SQLException {
        conn.close();
    }

    public List<Pokemon> listar(Connection conn) throws SQLException {
        String sql = "SELECT id, url, nombre, nivel, tipo FROM pokemon";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Pokemon> pokemones = new ArrayList<>();
        while (rs.next()) {
            pokemones.add(new Pokemon(
                    rs.getLong("id"),
                    rs.getString("url"),
                    rs.getString("nombre"),
                    rs.getString("tipo"),
                    rs.getInt("nivel")
            ));
        }

        return pokemones;
    }

    public List<Pokemon> listar(Connection conn, long idUsuario) throws SQLException {
        String sql = "SELECT p.id as id, p.url as url ,p.nombre as nombre, "
                + "p.nivel as nivel, p.tipo as tipo"
                + "FROM pokemonxusuario pu INNER JOIN pokemon p ON (pu.pokemonid = p.id) "
                + "WHERE pu.usuarioid=?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setLong(1, idUsuario);

        ResultSet rs = ps.executeQuery();

        List<Pokemon> pokemones = new ArrayList<>();
        while (rs.next()) {
            pokemones.add(new Pokemon(
                    rs.getLong("id"),
                    rs.getString("url"),
                    rs.getString("nombre"),
                    rs.getString("tipo"),
                    rs.getInt("nivel")
            ));
        }

        return pokemones;
    }

    public List<Integer> obtenerDisponibles(Connection conn, int minuto) throws SQLException {
        String sql;
        int limite = -1;
        
        if (minuto - 15 < 0) {
            limite = 1440 - (15 - minuto);
            sql = "SELECT id FROM pokemon WHERE ABS(? - minuto) <= 15 OR minuto >= ?";
        } else if (minuto + 15 > 1439) {
            limite = 15 - (1440 - minuto);
            sql = "SELECT id FROM pokemon WHERE ABS(? - minuto) <= 15 OR minuto <= ?";
        } else {
            sql = "SELECT id FROM pokemon WHERE ABS(? - minuto) <= 15 OR 0 = ?" ;
        }

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, minuto);
        ps.setInt(2, limite);

        ResultSet rs = ps.executeQuery();

        List<Integer> idPokemones = new ArrayList<>();
        while (rs.next()) {
            idPokemones.add(rs.getInt("minuto"));
        }

        return idPokemones;
    }

    public void capturarPokemon(Connection conn, int usuarioId, int pokemonId) throws SQLException {
        String sql = "INSERT INTO pokemonxusuario VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, usuarioId);
        ps.setInt(2, pokemonId);

        ps.execute();
    }
}
