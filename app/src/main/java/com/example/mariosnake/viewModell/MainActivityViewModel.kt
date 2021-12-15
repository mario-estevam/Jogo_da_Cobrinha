package com.example.mariosnake.viewModell

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivityViewModel: ViewModel() {

    var dificuldade = MutableLiveData<String>("facil")
    var tamanho = MutableLiveData<String>("padrao")
    var flagButton = "false" // flag passada pra activity the game que verifica se o jogo já foi inciado para ativar o botão continuar
    var texto = ""
    var texto2 = ""
    var score = "0"


    fun params(){
        if (texto == "facil") {
        dificuldade.value = "facil"
            Log.e("dificuldade",dificuldade.value.toString())
        } else if (texto == "medio"){
            dificuldade.value = "medio"
            Log.e("dificuldade",dificuldade.value.toString())
        } else if (texto == "dificil"){
            dificuldade.value = "dificil"
            Log.e("dificuldade", dificuldade.value.toString())
        }
        //verificando tamanho do tabuleiro
        if(texto2 =="padrao"){
            tamanho.value = "padrao"
        }
        if(texto2 == "pequeno") {
            tamanho.value = "pequeno"
        }

    }

    fun jogar(){

    }

    fun config(){

    }



}