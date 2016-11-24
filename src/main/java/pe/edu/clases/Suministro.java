/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.clases;

/**
 *
 * @author fixt
 */
public class Suministro {
    
    private String num;
    private int consumo;
    private String fecha;

    public Suministro(String num, int consumo, String fecha) {
        this.num = num;
        this.consumo = consumo;
        this.fecha = fecha;
    }

    public Suministro() {
    }
    
    

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    

    public Suministro(String num, int consumo) {
        this.num = num;
        this.consumo = consumo;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }
    
    
    
}
