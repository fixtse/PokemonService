package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.pokemon;

import java.util.List;

/**
 * Created by chfernandezrios on 7/10/2016.
 */
public class PokemonResponse {

    private String name;
    private int weight; // Atributo se utilizará para el nivel del pokemon
    private List<ElementoType> types;
    private Sprite sprites;

    public PokemonResponse(String name, int weight, List<ElementoType> types, Sprite sprites) {
        this.name = name;
        this.weight = weight;
        this.types = types;
        this.sprites = sprites;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public int getPeso() {
        return weight;
    }

    public void setPeso(int peso) {
        this.weight = peso;
    }

    public List<ElementoType> getElementosTipo() {
        return types;
    }

    public void setElementosTipo(List<ElementoType> elementosTipo) {
        this.types = elementosTipo;
    }

    public String getImagenUrl() {
        return sprites.getUrl();
    }

    public void setImagenUrl(String url) {
        this.sprites.setUrl(url);
    }
}
