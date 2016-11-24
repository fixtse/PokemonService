/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.clases;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Kevin
 */
public class Paciente implements Serializable{
    private String id;
    private String nombres;
    private String paterno;
    private String materno;
    private String fecha_nacimiento;
    private String correo;
    private int checkin;

    public Paciente() {
    }

    public Paciente(String id, String nombres, String paterno, String materno, String fecha_nacimiento, String correo) {
        this.id = id;
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.fecha_nacimiento = fecha_nacimiento;
        this.correo = correo;
    }
    
    

    public Paciente(String id, String nombres, String paterno, String materno, String fecha_nacimiento, String correo, int checkin) {
        this.id = id;
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.fecha_nacimiento = fecha_nacimiento;
        this.correo = correo;
        this.checkin = checkin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCheckin() {
        return checkin;
    }

    public void setCheckin(int checkin) {
        this.checkin = checkin;
    }
    
    

    

    
    
    
    
}
