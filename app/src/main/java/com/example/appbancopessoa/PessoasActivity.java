package com.example.appbancopessoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appbancopessoa.model.Pessoa;
import com.example.appbancopessoa.model.PessoaFisica;
import com.example.appbancopessoa.model.PessoaJuridica;

import java.sql.Date;
import java.util.List;

public class PessoasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoas);

        Intent it = getIntent();
        Bundle resultado = it.getExtras();

        boolean isnulo = resultado == null;

        if (!isnulo) {

            if (resultado.getString("tipodocumento").equals("F")) {

                PessoaFisica pf = new PessoaFisica();
                pf.setNome(resultado.getString("meunomerazao"));
                pf.setDocumento(resultado.getString("meudocumento"));
                pf.setTipotelefone(resultado.getString("tipotelefone"));
                pf.setTelefone(resultado.getString("meutelefone"));
                pf.setDtNascimento(Date.valueOf(resultado.getString("minhadata")));


                long idPessoa = pf.Cadastrar(PessoasActivity.this);
            } else {

                PessoaJuridica pj = new PessoaJuridica();
                pj.setRazaoSocial(resultado.getString("meunomerazao"));
                pj.setDocumento(resultado.getString("meudocumento"));
                pj.setTipotelefone(resultado.getString("tipotelefone"));
                pj.setTelefone(resultado.getString("meutelefone"));
                pj.setDtAbertura(Date.valueOf(resultado.getString("minhadata")));

                long idPessoa = pj.Cadastrar(PessoasActivity.this);
            }
//            //Codigo para carregar list view de empresas
//
//            Pessoa pj = new PessoaJuridica();
//            List<String> empresas =  pj.Listar(PessoasActivity.this);
//            ListView lista = findViewById(R.id.ltvPessoas);
//            ArrayAdapter<String> arrAdapterLista =
//                    new ArrayAdapter<>(PessoasActivity.this,
//                            android.R.layout.simple_list_item_1,
//                            empresas
//                    );
//            lista.setAdapter(arrAdapterLista);
        }
    }
    public void ListarPF (View v) {

        PessoaFisica pf = new PessoaFisica();
        ListView ltvPessoas = findViewById(R.id.ltvPessoas);

        List<String>pessoas = pf.Listar(this);
        ArrayAdapter<String> arrAdapterLista =
                new ArrayAdapter<>(PessoasActivity.this,
                        android.R.layout.simple_list_item_1,
                        pessoas
                );
        ltvPessoas.setAdapter(arrAdapterLista);
    }
    public void ListarPJ (View v) {

        PessoaJuridica pj = new PessoaJuridica();
        ListView ltvEmpresas = findViewById(R.id.ltvPessoas);

        List<String>empresas = pj.Listar(this);
        ArrayAdapter<String> arrAdapterLista =
                new ArrayAdapter<>(PessoasActivity.this,
                        android.R.layout.simple_list_item_1,
                        empresas
                );
        ltvEmpresas.setAdapter(arrAdapterLista);
    }
    public void voltarPrincipal(View v) {
        Intent it2 = new Intent(PessoasActivity.this, MainActivity.class);
        startActivity(it2);
    }
}
