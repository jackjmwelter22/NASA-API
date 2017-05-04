package com.example.nasaapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NasaApi {
    @GET("planetary/apod")
    Call<NasaData> getApod(@Query("date") String date);

}
