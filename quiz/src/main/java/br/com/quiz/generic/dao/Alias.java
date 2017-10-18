package br.com.quiz.generic.dao;

import java.io.Serializable;
import org.hibernate.sql.JoinType;

public class Alias implements Serializable {

    private static final long serialVersionUID = 0x6eea8564d6e0ddd6L;
    
    private String atributo;
    private String aliasAtributo;
    private JoinType tipoDeJoin;
    
    public Alias(String atributo, String aliasAtributo){
        this.atributo = atributo;
        this.aliasAtributo = aliasAtributo;
        tipoDeJoin = null;
    }

    public Alias(String atributo, String aliasAtributo, JoinType tipoDeJoin){
        this.atributo = atributo;
        this.aliasAtributo = aliasAtributo;
        this.tipoDeJoin = tipoDeJoin;
    }

    public String getAtributo(){
        return atributo;
    }

    public void setAtributo(String atributo){
        this.atributo = atributo;
    }

    public String getAliasAtributo(){
        return aliasAtributo;
    }

    public void setAliasAtributo(String aliasAtributo){
        this.aliasAtributo = aliasAtributo;
    }

    public JoinType getTipoDeJoin(){
        return tipoDeJoin;
    }

    public void setTipoDeJoin(JoinType tipoDeJoin){
        this.tipoDeJoin = tipoDeJoin;
    }
}
