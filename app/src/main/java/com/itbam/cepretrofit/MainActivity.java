package com.itbam.cepretrofit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.itbam.cepretrofit.databinding.ActivityMainBinding;
import com.itbam.cepretrofit.model.DadosCep;
import com.itbam.cepretrofit.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        componentes();
    }

    public void componentes(){
        binding.btnCep.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cep:
                getDadosCep();
                break;
        }
    }

    public void getDadosCep() {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        Call<DadosCep> call = retrofitConfig.api.getBuscarCEP(binding.editCep.getText().toString());
        call.enqueue(new Callback<DadosCep>() {
            @Override
            public void onResponse(Call<DadosCep> call, Response<DadosCep> response) {
                Log.e("Sucesso", response.message());
                if (response.isSuccessful() && response.body() != null) {
                   DadosCep cep = response.body();
                    String strCep = "Cep: " + cep.getCep() + " " +
                            "Logradouro: " + cep.getLogradouro() + " " +
                            "Bairro: " +    cep.getBairro() + " " +
                            "Cidade: " + cep.getLocalidade() + " " +
                            "Estado: " + cep.getUf();
                    binding.textDados.setText(strCep);
                   // binding.textDados.setText(response.body().toString());
                }else {
                    Log.e("CEPService   ", "Erro ao buscar o cep:");
                }
            }

            @Override
            public void onFailure(Call<DadosCep> call, Throwable t) {
                Log.e("CEPService   ", "Erro ao buscar o cep:" + t.getMessage());
            }
        });
    }

    public void dados() {

    }

}