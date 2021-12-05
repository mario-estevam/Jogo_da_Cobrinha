package com.example.mariosnake

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.mariosnake.R.layout.activity_config
import com.example.mariosnake.databinding.ActivityConfigBinding


lateinit var bindingC: ActivityConfigBinding

class ActivityConfig : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingC = DataBindingUtil.setContentView(this, activity_config)
        val param = intent.extras
        val texto = param?.getString("nivel")
        val texto2 = param?.getString("tabuleiro")

        if(texto.toString() == "facil"){
            bindingC.facil.isChecked =  true
        } else if ( texto.toString() == "medio"){
            bindingC.medio.isChecked = true
        } else if ( texto.toString() == "dificil"){
            bindingC.dificil.isChecked = true
        }

        if(texto2.toString()=="padrao"){
            bindingC.padrao.isChecked = true
        } else if (texto2.toString() == "pequeno"){
            bindingC.peq.isChecked = true
        }
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

        var intent = Intent()
        var parameter = Bundle()
        parameter.putString("nivel", dif)
        parameter.putString("tabuleiro", tab)
        intent.putExtras(parameter)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}