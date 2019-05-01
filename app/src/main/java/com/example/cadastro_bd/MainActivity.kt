package com.example.cadastro_bd

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    internal lateinit var bt_entrar: Button
    internal lateinit var bt_cadastro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_entrar = findViewById<View>(R.id.bt_entrar) as Button
        bt_cadastro = findViewById<View>(R.id.bt_cadastro) as Button

        bt_entrar.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }

        bt_cadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}
