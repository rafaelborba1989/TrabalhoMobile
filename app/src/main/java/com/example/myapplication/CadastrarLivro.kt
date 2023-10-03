package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CadastrarLivro : AppCompatActivity() {

    private lateinit var binding:CadastrarLivroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_livro)
    }
}