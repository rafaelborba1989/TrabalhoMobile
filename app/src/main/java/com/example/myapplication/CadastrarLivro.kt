package ufrn.br.meuslivros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.example.myapplication.dataBase.AppDatabase
import com.example.myapplication.databinding.ActivityCadastrarLivroBinding
import com.example.myapplication.model.Livro

class CadastrarLivro : AppCompatActivity() {

    private lateinit var binding:ActivityCadastrarLivroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cadastrar_livro)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "meusLivros.sqlite"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()

        binding.buttonConfirmar.setOnClickListener{
            val stringAno = binding.editTextAno.text.toString()
            var livro:Livro = Livro(binding.editTextTitulo.text.toString() , binding.editTextAutor.text.toString(), stringAno.toInt(),
                binding.ratingBarLivro.rating.toInt())

            db.livroDao().inserir(livro)
            Toast.makeText(this, "Livro salvo!", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

        binding.buttonCancelar.setOnClickListener{
            onBackPressed()
        }
    }
}