package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto;

/**
 * Created by chfernandezrios on 7/10/2016.
 */
public class ElementoType {
    private int slot;
    private Type type;

    public ElementoType(int slot, Type type) {
        this.slot = slot;
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
