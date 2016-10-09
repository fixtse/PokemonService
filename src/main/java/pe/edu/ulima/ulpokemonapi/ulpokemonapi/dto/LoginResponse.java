package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto;

import pe.edu.ulima.ulpokemonapi.ulpokemonapi.model.Usuario;

public class LoginResponse {
    private String msg;
    private Status status;
    private Usuario usuario;

    public LoginResponse() {
    }

    public LoginResponse(String msg) {
        this.msg = msg;
    }

    public LoginResponse(String msg, Status status) {
        this.msg = msg;
        this.status = status;
    }

    public LoginResponse(String msg, Status status, Usuario usuario) {
        this.msg = msg;
        this.status = status;
        this.usuario = usuario;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    
}
