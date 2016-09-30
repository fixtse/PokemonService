
package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto;

public class LoginResponse {
    private String msg;
    private Status status;

    public LoginResponse() {
    }

    public LoginResponse(String msg) {
        this.msg = msg;
    }

    public LoginResponse(String msg, Status status) {
        this.msg = msg;
        this.status = status;
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
