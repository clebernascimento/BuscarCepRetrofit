package com.itbam.cepretrofit.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private Retrofit retrofit;
    public CepAPI api;

    public RetrofitConfig() {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.level(HttpLoggingInterceptor.Level.BODY);
        httpBuilder.addInterceptor(log);
        this.retrofit = new Retrofit
                .Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpBuilder.build())
                .build();

        api =getCepService();
    }

    public CepAPI getCepService(){
        return this.retrofit.create(CepAPI.class);
    }
}
