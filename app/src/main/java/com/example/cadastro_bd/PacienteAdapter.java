package com.example.cadastro_bd;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PacienteAdapter extends BaseAdapter {

    private List<Paciente> pacientes;
    private Activity activity;

    public PacienteAdapter(Activity activity, List<Paciente> pacientes) {

        this.activity= activity;
        this.pacientes = pacientes;

    }

    @Override
    public int getCount() {
        return pacientes.size();
    }

    @Override
    public Object getItem(int i) {
        return pacientes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) this.pacientes.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        View v = activity.getLayoutInflater().inflate(com.example.cadastro_bd.R.layout.item, viewGroup, false);
        TextView nome= v.findViewById(R.id.txt_nome);
        TextView cpf= v.findViewById(R.id.txt_cpf);
        TextView telefone= v.findViewById(R.id.txt_telefone);

        Paciente a = pacientes.get(i);

        nome.setText(a.getNome());
        cpf.setText(a.getCpf());
        telefone.setText(a.getTelefone());

        return v;
    }
}