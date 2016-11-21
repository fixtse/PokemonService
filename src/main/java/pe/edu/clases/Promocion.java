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
public class Promocion {
    private String id;
    private String nombre;
    private String descripcion;
    private int cantidad_checkins;
    private String fecha_inicio;
    private String fecha_fin;
    private String url;

    public Promocion() {
    }

    public Promocion(String id, String nombre, String descripcion, int cantidad_checkins, String fecha_inicio, String fecha_fin, String url) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad_checkins = cantidad_checkins;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad_checkins() {
        return cantidad_checkins;
    }

    public void setCantidad_checkins(int cantidad_checkins) {
        this.cantidad_checkins = cantidad_checkins;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }


    
    
}
