package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.pokemon;

/**
 *
 * @author chfernandezrios
 */
public class ElementoType {
   private Type type;

    public ElementoType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
