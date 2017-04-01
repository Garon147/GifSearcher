package orion.garon.gifsearcher.rest;

import orion.garon.gifsearcher.rest.data.GifList;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by VKI on 01.04.2017.
 */

public interface GifMethods {

    @GET("v1/gifs/trending?api_key=dc6zaTOxFJmzC")
    Call<GifList> gifs();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.giphy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
