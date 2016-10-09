package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto;

/**
 *
 * @author chfernandezrios
 */
public class PokemonResponse {
    private PokeAPIResponse datos;
    private String url;

    public PokemonResponse(PokeAPIResponse datos, String url) {
        this.datos = datos;
        this.url = url;
    }

    public PokeAPIResponse getDatos() {
        return datos;
    }

    public void setDatos(PokeAPIResponse datos) {
        this.datos = datos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
