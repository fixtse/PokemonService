package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.usuario;

import pe.edu.ulima.ulpokemonapi.ulpokemonapi.model.Usuario;

public class RegistroRequest {
    private Usuario usuario;

    public RegistroRequest() {
    }

    public RegistroRequest(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}