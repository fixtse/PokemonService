package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.pokemon;

public class PokemonDisponible {
    private int pokemonId;
    private String pokemonNombre;
    private String pokemonImagenUrl;

    public PokemonDisponible(int pokemonId, String pokemonNombre, String pokemonImagenUrl) {
        this.pokemonId = pokemonId;
        this.pokemonNombre = pokemonNombre;
        this.pokemonImagenUrl = pokemonImagenUrl;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getPokemonNombre() {
        return pokemonNombre;
    }

    public void setPokemonNombre(String pokemonNombre) {
        this.pokemonNombre = pokemonNombre;
    }

    public String getPokemonImagenUrl() {
        return pokemonImagenUrl;
    }

    public void setPokemonImagenUrl(String pokemonImagenUrl) {
        this.pokemonImagenUrl = pokemonImagenUrl;
    }

}
