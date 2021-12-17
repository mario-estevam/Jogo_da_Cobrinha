package com.example.mariosnake.viewModell

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivityViewModel: ViewModel() {

    var dificuldade = MutableLiveData<String>("facil")
    var tamanho = MutableLiveData<String>("padrao")
    var flagButton = "false" // flag passada pra activity the game que verifica se o jogo já foi inciado para ativar o botão continuar
    var dificuldadeP = ""
    var tabuleiroP = ""
    var score = "0"


    fun params(){
        if (dificuldadeP == "facil") {
        dificuldade.value = "facil"
            Log.e("dificuldade",dificuldade.value.toString())
        } else if (dificuldadeP == "medio"){
            dificuldade.value = "medio"
            Log.e("dificuldade",dificuldade.value.toString())
        } else if (dificuldadeP == "dificil"){
            dificuldade.value = "dificil"
            Log.e("dificuldade", dificuldade.value.toString())
        }else if(dificuldadeP == "mtd"){
            dificuldade.value = "mtd"
        }
        //verificando tamanho do tabuleiro
        if(tabuleiroP =="padrao"){
            tamanho.value = "padrao"
        }
        if(tabuleiroP == "pequeno") {
            tamanho.value = "pequeno"
        }

    }

    fun jogar(){

    }

    fun config(){

    }



}