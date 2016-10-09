package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.pokemon;

/**
 * Created by chfernandezrios on 7/10/2016.
 */
public class Type {
    private String name;

    public Type(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
