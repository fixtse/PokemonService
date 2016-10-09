package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto;

/**
 * Created by chfernandezrios on 7/10/2016.
 */
public class Type {
    private String url;
    private String name;

    public Type(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
