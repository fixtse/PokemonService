package pe.edu.ulima.ulpokemonapi.ulpokemonapi.model;

public class Pokemon {
    private long id;
    private String url;
    private String nombre;
    private String tipo;
    private int nivel;

    public Pokemon() {
    }

    public Pokemon(long id, String url, String nombre, String tipo, int nivel) {
        this.id = id;
        this.url = url;
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
}
