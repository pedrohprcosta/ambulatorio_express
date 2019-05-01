package com.example.cadastro_bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name= "banco.db";
    private static final int version= 1;

    public Conexao (Context context){
        super(context, name, null, version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

       String crier ="CREATE TABLE paci(id INTEGER PRIMARY KEY AUTOINCREMENT, NOME VARCHAR(50), CPF VARCHAR(50), TELEFONE VARCHAR(120));";
       db.execSQL(crier);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}