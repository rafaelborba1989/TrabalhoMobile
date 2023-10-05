package com.example.livros

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myapplication.dataBase.AppDatabase
import com.example.myapplication.databinding.ActivityListarLivrosBinding
import com.example.myapplication.model.Livro

class ListarLivros : AppCompatActivity() {

    private lateinit var binding: ActivityListarLivrosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarLivrosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "livro.sqlite"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()

        val listLivros:List<Livro> = db.livroDao().listar()


        if(listLivros.isNotEmpty()){

            var cont = 0

            fun updateBookData() {
                val livro = listLivros[cont]
                binding.textViewNomeListar.text = livro.nome
                binding.textViewAnoListar.text = livro.ano.toString()
                binding.textViewNotaListar.text = livro.nota.toString()
            }

            updateBookData()

            binding.buttonProximo.setOnClickListener {
                if (cont + 1 < listLivros.size) {
                    cont++
                    updateBookData()
                } else {
                    Toast.makeText(this, "Sem livros após esse!", Toast.LENGTH_SHORT).show()
                }
            }

            binding.buttonAnterior.setOnClickListener {
                if (cont - 1 >= 0) {
                    cont--
                    updateBookData()
                } else {
                    Toast.makeText(this, "Sem livros antes desse!", Toast.LENGTH_SHORT).show()
                }
            }

        }



    }
}