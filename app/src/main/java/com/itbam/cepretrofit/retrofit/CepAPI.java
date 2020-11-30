package com.itbam.cepretrofit.retrofit;

import com.itbam.cepretrofit.model.DadosCep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CepAPI {
    @GET("{cep}/json/")
    Call<DadosCep> getBuscarCEP(
            @Path("cep") String cep
    );
}
