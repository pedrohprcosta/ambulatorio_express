package com.example.cadastro_bd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PacienteActivity extends AppCompatActivity {

    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private PacienteDAO dao;
    private Paciente paciente = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);

        nome = findViewById(R.id.editNome);
        cpf = findViewById(R.id.editCPF);
        telefone = findViewById(R.id.editTelefone);
        dao = new PacienteDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("paciente")) {
            paciente = (Paciente) it.getSerializableExtra("paciente");
            nome.setText(paciente.getNome());
            cpf.setText(paciente.getCpf());
            telefone.setText(paciente.getTelefone());
        }

    }

    public void salvar(View view) {
        if (paciente == null) {
            paciente = new Paciente();
            paciente.setNome(nome.getText().toString());
            paciente.setCpf(cpf.getText().toString());
            paciente.setTelefone(telefone.getText().toString());
            long id = dao.inserir(paciente);
            Toast.makeText(this, "Paciente inserido com id:" + id, Toast.LENGTH_SHORT).show();
        } else {
            paciente.setNome(nome.getText().toString());
            paciente.setCpf(cpf.getText().toString());
            paciente.setTelefone(telefone.getText().toString());
            dao.atualizar(paciente);
            Toast.makeText(this, "Paciente foi atualizado", Toast.LENGTH_SHORT).show();
        }
    }
}
