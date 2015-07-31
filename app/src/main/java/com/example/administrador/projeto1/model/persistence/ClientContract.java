package com.example.administrador.projeto1.model.persistence;

import android.content.ContentValues;

public class ClientContract {
    public static final String TABLE = "client";

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String AGE = "age";

    public static final String PHONE = "phone";

    //public static final String ADDRESS = "address";

    public static  final String CEP = "cep";

    public static  final String TIPODELOGRADOURO = "tipodelogradouro";

    public static  final String LOGRADOURO = "logradouro";

    public static  final String BAIRRO = "bairro";

    public static  final String CIDADE = "cidade";

    public static  final String ESTADO = "estado";

    public static final String[] COLUNS = {ID, NAME, AGE, PHONE, CEP, TIPODELOGRADOURO,LOGRADOURO,BAIRRO,CIDADE,ESTADO};

    public static String getSqlCreatTable() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(NAME + " TEXT, ");
        sql.append(AGE + " TEXT ,");
        sql.append(PHONE + " TEXT ,");
        sql.append(CEP + " TEXT ,");
        sql.append(TIPODELOGRADOURO + " TEXT , ");
        sql.append(LOGRADOURO + " TEXT , ");
        sql.append(BAIRRO + " TEXT , ");
        sql.append(CIDADE + " TEXT , ");
        sql.append(ESTADO + " TEXT ");
        sql.append(" ) ");
        return sql.toString();
    }

}
