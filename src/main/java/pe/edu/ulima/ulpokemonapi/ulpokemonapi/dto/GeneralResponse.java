package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto;

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
