package com.example.appbancopessoa.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.appbancopessoa.util.BancoDados;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridica extends Pessoa {

    private String razaoSocial;
    private Date dtAbertura;

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Date getDtAbertura() {
        return dtAbertura;
    }

    public void setDtAbertura(Date dtAbertura) {
        this.dtAbertura = dtAbertura;
    }

    @Override
    public long Cadastrar(Context atividade) {
        try {
            BancoDados banco = new BancoDados(atividade);
            SQLiteDatabase db = banco.getWritableDatabase();
            ContentValues dados = new ContentValues();

            dados.put("razaosocial", this.getRazaoSocial());
            dados.put("tipodocumento", this.getTipodocumento());
            dados.put("documento", this.getDocumento());
            dados.put("tipotelefone", this.getTelefone());
            dados.put("telefone", this.getTelefone());
            dados.put("dtaceite", String.valueOf(this.getDtaceite()));
            dados.put("dtabertura", String.valueOf(this.getDtAbertura()));

            long idpessoa = db.insertOrThrow("pessoajuridica", null, dados);
            Log.d("Resultado PJ", String.valueOf(idpessoa));
            db.close();

            if (idpessoa > 0) {
                return idpessoa;
            } else {
                return 0;
            }
        } catch (SQLiteException e) {
            Log.d("Erro no Banco: ", e.getMessage());
            return 0;
        }
    }

    @Override
    public List<String> Listar(Context atividade) {


        try {

            BancoDados banco = new BancoDados(atividade);
            SQLiteDatabase db = banco.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM pessoajuridica;"
                    , null);
            List<String> nomes = new ArrayList<>();
            while (c.moveToNext()) {

                    nomes.add(c.getString(
                            c.getColumnIndex("razaosocial")));

            }
            db.close();

            return nomes;

        } catch (SQLiteException e) {
            Log.d("Erro no Banco: ", e.getMessage());
            return null;
        }


    }
}