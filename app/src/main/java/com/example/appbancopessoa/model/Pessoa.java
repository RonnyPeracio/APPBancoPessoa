package com.example.appbancopessoa.model;

import android.content.Context;

import java.util.Date;
import java.util.List;

public abstract class Pessoa {
    private int id;
    private String documento;
    private String telefone;
    private String tipodocumento;
    private String tipotelefone;
    private Date dtaceite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getTipotelefone() {
        return tipotelefone;
    }

    public void setTipotelefone(String tipotelefone) {
        this.tipotelefone = tipotelefone;
    }

    public Date getDtaceite() {
        return dtaceite;
    }

    public void setDtaceite(Date dtaceite) {
        this.dtaceite = dtaceite;
    }
    public abstract long Cadastrar(Context atividade);

    public abstract List<String> Listar(Context atividade);

}
