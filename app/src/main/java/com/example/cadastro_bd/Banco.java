package com.example.cadastro_bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static int versao= 1;
    private static String name= "Login_Registro_BaseDeDados.BD";

    public Banco(Context context) {
        super(context, name, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String crier= "CREATE TABLE Utilizador(id TEXT PRIMARY KEY, nome TEXT, senha TEXT);";
        db.execSQL(crier);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Utilizador");
        onCreate(db);

    }

    public long CriarUtilizador(String id, String nome, String senha){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("nome", nome);
        cv.put("senha", senha);
        long result = db.insert("Utilizador", null, cv);
        return result;

    }

    public String ValidarLogin(String id, String senha){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Utilizador WHERE id = ? AND senha=?", new String[]{id,senha});
            if(c.getCount() > 0){
                return "OK";
            }

        return "ERRO";

    }

}