/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto;

import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.pokemon.PokeAPIResponse;
import pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto.characteristic.CharacteristicResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 * @author chfernandezrios
 */
public interface IPokeAPIClient {
    
    @GET("pokemon/{id_pokemon}")
    Call<PokeAPIResponse> obtenerPokemon(@Path("id_pokemon") int id);
    
    @GET("characteristic/{id_pokemon}")
    Call<CharacteristicResponse> obtenerDescripcion(@Path("id_pokemon") int id);
    
}
