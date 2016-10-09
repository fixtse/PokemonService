package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto;

import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.usuario.Status;

public class GeneralResponse {
    private Status status;

    public GeneralResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
}
