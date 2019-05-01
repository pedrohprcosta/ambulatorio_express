package com.example.cadastro_bd

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CadastroActivity : AppCompatActivity() {

    internal lateinit var tc_login: EditText
    internal lateinit var tc_senha: EditText
    internal lateinit var tc_csenha: EditText
    internal var tc_nome: EditText? = null
    internal lateinit var bc_cadastro: Button

    internal lateinit var db: Banco

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        db = Banco(this)
        bc_cadastro = findViewById<View>(R.id.bc_cadastro) as Button



        bc_cadastro.setOnClickListener {
            tc_senha = findViewById(R.id.tc_senha)
            tc_csenha = findViewById<View>(R.id.tc_csenha) as EditText
            tc_nome = findViewById(R.id.tc_nome)
            tc_login = findViewById(R.id.tc_registro)
            val id = tc_login.text.toString()
            val nome = tc_nome!!.text.toString()
            val senha = tc_senha.text.toString()
            val confirmation_sena = tc_csenha.text.toString()

            if (id == "") {
                Toast.makeText(this@CadastroActivity, "Todos os campos terão que ser preenchidos, tente novamente", Toast.LENGTH_SHORT).show()
            } else if (senha != confirmation_sena) {
                Toast.makeText(this@CadastroActivity, "As senhas estão diferentes, tente novamente", Toast.LENGTH_SHORT).show()
            } else if (senha == "" || confirmation_sena == "") {
                Toast.makeText(this@CadastroActivity, "Senha inválida, tente novamente", Toast.LENGTH_SHORT).show()
            } else if (nome == "") {
                Toast.makeText(this@CadastroActivity, "Senha inválida, tente novamente", Toast.LENGTH_SHORT).show()

            } else {

                val res = db.CriarUtilizador(id, nome, senha)

                if (res > 0) {
                    Toast.makeText(this@CadastroActivity, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@CadastroActivity, "Cadastro inválido, tente novamente", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }
}

