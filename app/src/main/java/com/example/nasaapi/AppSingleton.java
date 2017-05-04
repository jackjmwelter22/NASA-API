package com.example.nasaapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Request;

import java.io.IOException;
import java.text.DateFormat;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppSingleton {
    private static final AppSingleton ourInstance = new AppSingleton();
    private NasaApi nasaApi;
    private Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();



    public static AppSingleton getInstance() {
        return ourInstance;
    }

    private AppSingleton() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                okhttp3.Request request = chain.request();
                                HttpUrl url = request.url().newBuilder().addQueryParameter("api_key", "z2LJ69izlJb1dxzvcBpfxva8DGgZULTsRz060fLe").build();
                                request = request.newBuilder().url(url).build();
                                return chain.proceed(request);
                            }
                        }).build())
                .build();
        nasaApi = retrofit.create(NasaApi.class);
    }

    public NasaApi getNasaApi() {
        return nasaApi;
    }
    public Gson getGson() {
        return gson;
    }
}
