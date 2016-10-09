/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.pokemon;

/**
 *
 * @author chfernandezrios
 */
public class Sprite {
    private String front_default;

    public Sprite(String url) {
        this.front_default = url;
    }

    public String getUrl() {
        return front_default;
    }

    public void setUrl(String url) {
        this.front_default = url;
    }
    
}
