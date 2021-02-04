package com.example.appbancopessoa.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class BancoDados extends SQLiteOpenHelper {

    public BancoDados(@Nullable Context atividade) {
        super(atividade, "banco.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_pessoafisica = "CREATE TABLE pessoafisica (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "documento TEXT UNIQUE, tipodocumento TEXT, nome TEXT, tipotelefone TEXT, " +
                "telefone TEXT, dtaceite DATE, dtnascimento DATE);";
        db.execSQL(sql_pessoafisica);
        String sql_pessoajuridica = "CREATE TABLE pessoajuridica (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "documento TEXT UNIQUE, tipodocumento TEXT, razaosocial TEXT, tipotelefone TEXT, " +
                "telefone TEXT, dtaceite DATE, dtabertura DATE);";
        db.execSQL(sql_pessoajuridica);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql_pessoafisica = "DROP TABLE IF EXISTS pessoafisica;";
        String sql_pessoajuridica = "DROP TABLE IF EXISTS pessoajuridica;";

        db.execSQL(sql_pessoafisica);
        db.execSQL(sql_pessoajuridica);

        onCreate(db);
    }

   }
