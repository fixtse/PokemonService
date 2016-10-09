package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.characteristic;

import java.util.List;

/**
 *
 * @author chfernandezrios
 */
public class CharacteristicResponse {
    private List<Description> descriptions;

    public CharacteristicResponse(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }
    
    
}
