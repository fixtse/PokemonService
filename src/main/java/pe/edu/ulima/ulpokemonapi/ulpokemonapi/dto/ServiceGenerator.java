package pe.edu.ulima.ulpokemonapi.ulpokemonapi.dto;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chfernandezrios on 2/10/2016.
 */
public class ServiceGenerator {

    public static final String API_POKE_URL = "http://pokeapi.co/api/v2/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder servicioPokemonBuilder = new Retrofit.Builder().baseUrl(API_POKE_URL).addConverterFactory(GsonConverterFactory.create());
    //private static Retrofit.Builder pokeAPIBuilder = new Retrofit.Builder().baseUrl(API_POKE_URL).addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        //Retrofit retrofit = null;
        //if (serviceClass == IPokemonClient.class) {
           Retrofit retrofit = servicioPokemonBuilder.client(httpClient.build()).build();
        //} else if (serviceClass == IPokeAPIClient.class){
        //    retrofit = pokeAPIBuilder.client(httpClient.build()).build();
        //}
        return retrofit.create(serviceClass);
    }
}
