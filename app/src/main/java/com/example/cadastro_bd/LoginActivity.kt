package com.example.cadastro_bd

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    internal lateinit var t_login: EditText
    internal lateinit var t_senha: EditText
    internal lateinit var b_entrar: Button

    internal lateinit var db: Banco


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        db = Banco(this)



        b_entrar = findViewById<View>(R.id.b_entrar) as Button

        b_entrar.setOnClickListener {
            t_login = findViewById<View>(R.id.t_login) as EditText
            t_senha = findViewById<View>(R.id.t_senha) as EditText

            val id = t_login.text.toString()
            val senha = t_senha.text.toString()

            val teste = db.ValidarLogin(id,senha)

            if(teste.equals("OK")){
                Toast.makeText(this@LoginActivity, "Logou", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListarActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this@LoginActivity, "Falha no login", Toast.LENGTH_SHORT).show()
            }

        }
    }
}