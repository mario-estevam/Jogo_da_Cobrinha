package com.example.mariosnake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mariosnake.databinding.ActivityMainBinding

lateinit var binding:ActivityMainBinding
lateinit var viewModel: MainActivityViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider (this,).get(MainActivityViewModel::class.java)

        val activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
                RESULT_OK->{
                    val param = it.data?.extras
                    val texto = param?.getString("nivel")
                    val texto2 = param?.getString("tabuleiro")

                    if (texto.toString() == "facil") {
                        viewModel.dificuldade.value = "facil"
                    } else if (texto.toString() == "medio"){
                        viewModel.dificuldade.value = "medio"
                    } else if (texto.toString() == "dificil"){
                        viewModel.dificuldade.value = "dificil"
                    }
                    //verificando tamanho do tabuleiro
                    if(texto2.toString()=="padrao"){
                        viewModel.tamanho.value = "padrao"
                    }
                    if(texto2.toString() == "pequeno") {
                        viewModel.tamanho.value = "pequeno"
                    }

                }
                RESULT_CANCELED ->{

                }

            }
        }


//        viewModel.dificuldade.observe(this, Observer {
//            var dificuldade = it.toString()
//        })
//
//        viewModel.tamanho.observe(this, Observer {
//            var tamanho = it.toString()
//        })


        binding.jogar.setOnClickListener {
            if(viewModel.flagButton == true){
                binding.continuar.visibility = View.VISIBLE
            }
            val intent = Intent(this,TheGameActivity::class.java)
            val parametro = Bundle()
            parametro.putString("nivel", viewModel.dificuldade.value.toString())
            parametro.putString("tabuleiro", viewModel.tamanho.value.toString())
            intent.putExtras(parametro)
            activityResult.launch(intent)
        }

        binding.config.setOnClickListener {
            val intent = Intent(this, ActivityConfig::class.java)
            val parametro = Bundle()
            parametro.putString("nivel", viewModel.dificuldade.value.toString() )
            parametro.putString("tabuleiro", viewModel.tamanho.value.toString())
            intent.putExtras(parametro)
            activityResult.launch(intent)
        }


    }

    override fun onStart() {
        super.onStart()
        val preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE)
        viewModel.dificuldade.value = preferences.getString("dificuldadeSave", "facil")
        viewModel.tamanho.value = preferences.getString("tamanhoSave", "padrao")
    }

    override fun onStop() {
        super.onStop()
        val preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("dificuldadeSave", viewModel.dificuldade.value.toString())
        editor.putString("tamanhoSave", viewModel.tamanho.value.toString())
        editor.apply()
    }
}