package com.example.administrador.projeto1.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "MY DATABASE";
    private static final int VERSION = 2;

    public DataBaseHelper(Context context) {
        super(context, DataBaseHelper.BANCO_DADOS, null, DataBaseHelper.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ClientContract.getSqlCreatTable());
        db.execSQL(UsuarioContract.getSqlCreatTable());
        db.execSQL(UsuarioContract.inserir());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
