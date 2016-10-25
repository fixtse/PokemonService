/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.usuario;

/**
 *
 * @author fixt
 */
public class NumReq {
    
    private Float num1,num2;

    public NumReq(Float num1, Float num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public Float getNum1() {
        return num1;
    }

    public void setNum1(Float num1) {
        this.num1 = num1;
    }

    public Float getNum2() {
        return num2;
    }

    public void setNum2(Float num2) {
        this.num2 = num2;
    }
    
    
    
    
}
