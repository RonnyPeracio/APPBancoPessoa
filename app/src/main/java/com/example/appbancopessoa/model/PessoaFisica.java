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

public class PessoaFisica extends Pessoa {

    private String nome;
    private Date dtNascimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtNascimento() {return dtNascimento; }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    @Override
    public long Cadastrar(Context atividade) {
        try {
            BancoDados banco = new BancoDados(atividade);
            SQLiteDatabase db = banco.getWritableDatabase();
            ContentValues dados = new ContentValues();

            dados.put("nome", this.getNome());
            dados.put("tipodocumento", this.getTipodocumento());
            dados.put("documento", this.getDocumento());
            dados.put("tipotelefone", this.getTelefone());
            dados.put("telefone", this.getTelefone());
            dados.put("dtaceite", String.valueOf(this.getDtaceite()));
            dados.put("dtnascimento", String.valueOf(this.getDtNascimento()));

            long idpessoa = db.insertOrThrow("pessoafisica", null, dados);
            Log.d("Resultado PF", String.valueOf(idpessoa));
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

        BancoDados banco = new BancoDados(atividade);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM pessoafisica;"
                , null);
        List<String> nomes = new ArrayList<>();
        while (c.moveToNext()) {
                nomes.add(c.getString(
                        c.getColumnIndex("nome")));
        }
        db.close();
        return nomes;
    }
}


