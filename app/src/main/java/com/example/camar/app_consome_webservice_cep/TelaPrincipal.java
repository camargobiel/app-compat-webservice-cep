package com.example.camar.app_consome_webservice_cep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.camar.app_consome_webservice_cep.R.id.tv_tipo_logradouro;

public class TelaPrincipal extends AppCompatActivity {

    TextView tv_cidade, tv_bairro, tv_logradouro, tv_tipo_logradouro;

    EditText edt_cep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        tv_cidade = (TextView)findViewById(R.id.tv_cidade);
        tv_bairro = (TextView)findViewById(R.id.tv_bairro);
        tv_logradouro = (TextView)findViewById(R.id.tv_logradouro);
        tv_tipo_logradouro = (TextView)findViewById(R.id.tv_tipo_logradouro);

        edt_cep = (EditText)findViewById(R.id.edt_cep);
    }

    public void buscar_clique(View v)
    {
        String cep = edt_cep.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<Endereco> call = api.buscarCEP(cep);

        call.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                Endereco endereco = response.body();

                String cidade = endereco.getCidade();
                String uf = endereco.getUf();
                String bairro = endereco.getBairro();
                String tipo_logradouro = endereco.getTipo_logradouro();
                String nome_logradouro = endereco.getLogradouro();

                tv_cidade.setText(cidade + " - " +uf);
                tv_bairro.setText(bairro);
                tv_logradouro.setText(tipo_logradouro + " " + nome_logradouro);
                tv_tipo_logradouro.setText(tipo_logradouro);
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "FALHA NA COMUNICAÇÃO!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
