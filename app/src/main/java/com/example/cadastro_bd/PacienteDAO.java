package com.example.cadastro_bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public PacienteDAO(Context context){

        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Paciente paciente){

        ContentValues values = new ContentValues();
        values.put("nome", paciente.getNome());
        values.put("cpf", paciente.getCpf());
        values.put("telefone", paciente.getTelefone());
        return banco.insert("paci", null, values);
    }

    public List<Paciente> obterTodos(){

        List<Paciente> pacientes = new ArrayList<>();
        Cursor cursor = banco.query("paci", new String[]{"id", "nome", "cpf", "telefone"},
                null, null, null, null, null);

        while (cursor.moveToNext()){

            Paciente a = new Paciente();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setCpf(cursor.getString(2));
            a.setTelefone(cursor.getString(3));
            pacientes.add(a);

        }
        return pacientes;
    }

    public void excluir(Paciente a){
        banco.delete("paci", "id = ?", new String[]{a.getId().toString()});
    }

    public void atualizar(Paciente paciente){

        ContentValues values = new ContentValues();
        values.put("nome", paciente.getNome());
        values.put("cpf", paciente.getCpf());
        values.put("telefone", paciente.getTelefone());
        banco.update("paci", values, "id = ?",
                new String[]{paciente.getId().toString()});
    }

}
