package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto;

import java.util.List;

/**
 * Created by chfernandezrios on 7/10/2016.
 */
public class PokeAPIResponse {
    private String name;
    private int weight; // Atributo se utilizará para el nivel del pokemon
    private List<ElementoType> types;

    public PokeAPIResponse(String name, int weight, List<ElementoType> types) {
        this.name = name;
        this.weight = weight;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<ElementoType> getTypes() {
        return types;
    }

    public void setTypes(List<ElementoType> types) {
        this.types = types;
    }
}
