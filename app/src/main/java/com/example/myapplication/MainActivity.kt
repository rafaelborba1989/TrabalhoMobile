package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.dataBase.AppDatabase
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val activityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == RESULT_OK){
            if (result.data != null) {
                Toast.makeText(baseContext, result.data!!.getStringExtra("msg"), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding  = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.cadastrar.setOnClickListener{
            val i = Intent(this, CadastrarLivro::class.java)
            activityLauncher.launch(i)
        }

        binding.listar.setOnClickListener{
            val i = Intent(this, ListarLivros::class.java)
            activityLauncher.launch(i)
        }


        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "banco_livro.sqlite"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()

    }
}