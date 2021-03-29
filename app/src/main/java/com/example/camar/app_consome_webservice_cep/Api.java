package com.example.camar.app_consome_webservice_cep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "http://cep.republicavirtual.com.br/";

    @GET("web_cep.php?formato=json")
    Call<Endereco> buscarCEP(@Query("cep") String cep);
}

