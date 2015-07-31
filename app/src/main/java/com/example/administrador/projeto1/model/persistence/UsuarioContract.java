package com.example.administrador.projeto1.model.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.projeto1.model.entities.AppUtil;
import com.example.administrador.projeto1.model.entities.Usuario;

/**
 * Created by Cardoso on 30/07/2015.
 */
public class UsuarioContract {

    public static final String TABLE = "usuario";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SENHA = "senha";

    public static final String[] COLUNS = {ID,NAME,SENHA};

    public static String getSqlCreatTable() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(NAME + " TEXT, ");
        sql.append(SENHA + " TEXT ");
        sql.append(" ) ");
        return sql.toString();
    }

    public static String inserir(){
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO usuario (id,name,senha) VALUES (1,'admin','admin') ");
        return sql.toString();
    }

    public static boolean vericacaoLogin(Usuario usuario){
         boolean valido = false;
        DataBaseHelper helper = new DataBaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(UsuarioContract.TABLE,UsuarioContract.COLUNS," name = '" + usuario.getName()+ "' AND senha = '" + usuario.getSenha()+"'",null,null,null,UsuarioContract.NAME);

        if(cursor.getCount() == 1){
            valido = true;
        }

        db.close();
        helper.close();

        return valido;
    }
}
