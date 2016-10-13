package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.pokemon;

/**
 * Created by chfernandezrios on 7/10/2016.
 */
public class PokemonDisponibleResponse {

    private String name;
    private Sprite sprites;

    public PokemonDisponibleResponse(String name, Sprite sprites) {
        this.name = name;
        this.sprites = sprites;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String name) {
        this.name = name;
    }

    public String getImagenUrl() {
        return sprites.getUrl();
    }

    public void setImagenUrl(String url) {
        this.sprites.setUrl(url);
    }

}
