package com.example.appbancopessoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appbancopessoa.model.PessoaFisica;
import com.example.appbancopessoa.model.PessoaJuridica;
import com.example.appbancopessoa.util.BancoDados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String tipoTelefone;
    public static String tipoDocumento;
    private static int aceite = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spnTipoTelefone = findViewById((R.id.spnTipoTelefone));

        String[] vetorTipoTelefone = {"Residencial",
                "Celular", "Trabalho"};
        ArrayList<String> TipoTelefone = new ArrayList<>(
                Arrays.asList(vetorTipoTelefone)
        );
        ArrayAdapter<String> arrAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, TipoTelefone);
        spnTipoTelefone.setAdapter(arrAdapter);

        spnTipoTelefone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.tipoTelefone = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void verificaCheckbox(View v) {

        CheckBox chkaceite = findViewById(R.id.chkAceite);
        boolean estaChecado = chkaceite.isChecked();

        if (estaChecado == true) {
            Toast.makeText(this, "Termos Aceitos", Toast.LENGTH_SHORT).show();
            MainActivity.aceite = 1;
        } else {
            MainActivity.aceite = 0;
        }

    }

    public void verificaPessoa(View v) {

        boolean estaCheckado = ((RadioButton) v).isChecked();
        int idcomponent = v.getId();

        switch (idcomponent) {

            case R.id.rdbJuridica:
                if (estaCheckado) {
                    Toast.makeText(MainActivity.this,
                            "Pessoa Jurídica",
                            Toast.LENGTH_SHORT).show();
                    MainActivity.tipoDocumento = "J";
                }
                break;
            case R.id.rdbFisica:
                if (estaCheckado) {
                    Toast.makeText(MainActivity.this,
                            "Pessoa Física",
                            Toast.LENGTH_SHORT).show();
                    MainActivity.tipoDocumento = "F";
                }
                break;
        }
    }

    public void Salvar(View v) {
        if (aceite == 1) {

            EditText textoNomeRazao = findViewById(R.id.edtNomeRazao);
            EditText textoDocumento = findViewById(R.id.edtDocumento);
            EditText textoTelefone = findViewById(R.id.edtTelefone);
            EditText textoDt = findViewById(R.id.edtDtNascAbert);

            String nome = textoNomeRazao.getText().toString();
            String documento = textoDocumento.getText().toString();
            String telefone = textoTelefone.getText().toString();
            String datareg = textoDt.getText().toString() ;

            Bundle dados = new Bundle();
            dados.putString("meunomerazao", nome);
            dados.putString("tipodocumento", MainActivity.tipoDocumento);
            dados.putString("meudocumento", documento);
            dados.putString("tipotelefone", MainActivity.tipoTelefone);
            dados.putString("meutelefone", telefone);
            dados.putString("minhadata", datareg);
            dados.putInt("aceite", MainActivity.aceite);

            Intent it = new Intent(MainActivity.this, PessoasActivity.class);
            it.putExtras(dados);
            startActivity(it);
        } else {
            Toast.makeText(this, "Favor Ler e Aceitar os Termos", Toast.LENGTH_LONG).show();
        }

    }


    }


