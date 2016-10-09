package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.pokemon;

/**
 *
 * @author chfernandezrios
 */
public class AtraparRequest {
    private int pokemonid;
    private int usuarioid;

    public AtraparRequest(int pokemonid, int usuarioid) {
        this.pokemonid = pokemonid;
        this.usuarioid = usuarioid;
    }

    public int getPokemonid() {
        return pokemonid;
    }

    public void setPokemonid(int pokemonid) {
        this.pokemonid = pokemonid;
    }

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }
    
}
