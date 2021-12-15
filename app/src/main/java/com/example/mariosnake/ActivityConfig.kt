package com.example.mariosnake

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mariosnake.R.layout.activity_config
import com.example.mariosnake.databinding.ActivityConfigBinding
import com.example.mariosnake.viewModell.ActivityConfigViewModel


lateinit var bindingC: ActivityConfigBinding
lateinit var modelViewConfig: ActivityConfigViewModel

class ActivityConfig : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingC = DataBindingUtil.setContentView(this, activity_config)
        modelViewConfig = ViewModelProvider(this,).get(ActivityConfigViewModel::class.java)

        val param = intent.extras
        modelViewConfig.texto = param?.getString("nivel").toString()
        modelViewConfig.texto2 = param?.getString("tabuleiro").toString()
        modelViewConfig.recebe()
    }

    fun enviar(view: View){
        var dif = "" // variavel que ira levar o valor da dificuldade
        var tab = "" // variavel que irá levar o tamanho do tabuleiro

        when(bindingC.difs.checkedRadioButtonId){
            bindingC.facil.id->{
                dif = "facil"
            }
            bindingC.medio.id->{
                dif = "medio"
            }
            bindingC.dificil.id->{
                dif = "dificil"
            }
        }

        when(bindingC.tams.checkedRadioButtonId){
            bindingC.peq.id->{
                tab = "pequeno"
            }
            bindingC.padrao.id->{
                tab = "padrao"
            }
        }

        val intent = Intent()
        val parameter = Bundle()
        parameter.putString("nivel", dif)
        parameter.putString("tabuleiro", tab)
        intent.putExtras(parameter)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}