package com.example.mariosnake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import com.example.mariosnake.databinding.ActivityMainBinding
import com.example.mariosnake.viewModell.MainActivityViewModel

lateinit var binding:ActivityMainBinding
lateinit var viewModel: MainActivityViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider (this,).get(MainActivityViewModel::class.java)

        val activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
                RESULT_OK->{
                    val param = it.data?.extras
                    viewModel.dificuldadeP = param?.getString("nivel").toString()
                    viewModel.tabuleiroP = param?.getString("tabuleiro").toString()
                    viewModel.params()
                    viewModel.score = param?.getString("pontos").toString()
                    Log.e("pts", viewModel.score)
                    viewModel.flagButton = param?.getString("flagButton").toString()
                    if(viewModel.flagButton =="true"){
                        binding.continuar.visibility = View.VISIBLE
                    }

                }
                RESULT_CANCELED ->{
                    viewModel.flagButton = "false"
                    binding.continuar.visibility = View.GONE
                }
            }
        }


        binding.continuar.setOnClickListener{
            val intent = Intent(this,TheGameActivity::class.java)
            val parametro = Bundle()
            parametro.putString("nivel", viewModel.dificuldade.value.toString())
            parametro.putString("tabuleiro", viewModel.tamanho.value.toString())
            parametro.putString("pts", viewModel.score)
            parametro.putBoolean("estd", true)
            Log.e("pts", viewModel.score)
            intent.putExtras(parametro)
            activityResult.launch(intent)
        }

        binding.jogar.setOnClickListener {
            viewModel.flagButton = "false"
            binding.continuar.visibility = View.GONE
            val intent = Intent(this,TheGameActivity::class.java)
            val parametro = Bundle()
            parametro.putString("nivel", viewModel.dificuldade.value.toString())
            parametro.putString("tabuleiro", viewModel.tamanho.value.toString())
            parametro.putString("pts", "0")
            intent.putExtras(parametro)
            activityResult.launch(intent)
        }

        binding.config.setOnClickListener {
            viewModel.flagButton = "false"
            binding.continuar.visibility = View.GONE
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
        viewModel.score = preferences.getString("ptss","0").toString()
        viewModel.flagButton = preferences.getString("flgButton","false").toString()
        Log.i("flag", viewModel.flagButton)
        if(viewModel.flagButton == "true"){
            binding.continuar.visibility = View.VISIBLE
        }
        viewModel.dificuldade.value = preferences.getString("dificuldadeSave", "facil")
        viewModel.tamanho.value = preferences.getString("tamanhoSave", "padrao")
    }

    override fun onStop() {
        super.onStop()
        val preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("flgButton", viewModel.flagButton)
        editor.putString("ptss", viewModel.score)
        editor.putString("dificuldadeSave", viewModel.dificuldade.value.toString())
        editor.putString("tamanhoSave", viewModel.tamanho.value.toString())
        editor.apply()
    }
}