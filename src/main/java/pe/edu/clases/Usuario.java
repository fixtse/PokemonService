/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.clases;

/**
 *
 * @author Kevin
 */
public class Usuario {
    private String id;
    private String nombre;
    private String contrasena;
    private int tipo;
    private String id_ref;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String contrasena, int tipo, String id_ref) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.id_ref = id_ref;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getId_ref() {
        return id_ref;
    }

    public void setId_ref(String id_ref) {
        this.id_ref = id_ref;
    }
    
    

    
    
    
    
}
