package com.example.cadastro_bd;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;


public class ListarActivity extends AppCompatActivity {

    private ListView listView;
    private PacienteDAO dao;
    private List<Paciente> pacientes;
    private List<Paciente> alunosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        listView = findViewById(R.id.lista_alunos);
        dao = new PacienteDAO(this);
        pacientes = dao.obterTodos();
        alunosFiltrados.addAll(pacientes);
        //ArrayAdapter<Paciente> adaptador = new ArrayAdapter<Paciente>(this, android.R.layout.simple_list_item_1, alunosFiltrados);
        PacienteAdapter adaptador = new PacienteAdapter(this, alunosFiltrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_consultar).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                procuraAluno(newText);

                return false;
            }
        });

        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void procuraAluno(String nome){

        alunosFiltrados.clear();
        for(Paciente a: pacientes){
            if (a.getNome().toLowerCase().contains(nome.toLowerCase())){
                alunosFiltrados.add(a);

            }
            if (a.getCpf().toLowerCase().contains(nome.toLowerCase())){
                alunosFiltrados.add(a);

            }
        }
        listView.invalidateViews();

    }

    public void excluir(MenuItem item){

        AdapterView.AdapterContextMenuInfo menuInfo= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Paciente pacienteExcluir = alunosFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir o Paciente?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alunosFiltrados.remove(pacienteExcluir);
                        pacientes.remove(pacienteExcluir);
                        dao.excluir(pacienteExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();

    }



    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, PacienteActivity.class);
        startActivity(it);
    }

    public void atualizar(MenuItem item){

        AdapterView.AdapterContextMenuInfo menuInfo= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Paciente pacienteAtualizar = alunosFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, PacienteActivity.class);
        it.putExtra("paciente", pacienteAtualizar);
        startActivity(it);

    }

    @Override
    public void onResume(){
        super.onResume();
        pacientes = dao.obterTodos();
        alunosFiltrados.clear();
        alunosFiltrados.addAll(pacientes);
        listView.invalidateViews();

    }

}
