package com.example.mariosnake

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivityViewModel: ViewModel() {

    var dificuldade = MutableLiveData<String>("facil")
    var tamanho = MutableLiveData<String>("padrao")
    var flagButton = false // flag passada pra activity the game que verifica se o jogo já foi inciado para ativar o botão continuar
    var texto = ""
    var texto2 = ""

    fun params(){
        if (texto == "facil") {
            viewModel.dificuldade.value = "facil"
            Log.e("dificuldade", viewModel.dificuldade.value.toString())
        } else if (texto == "medio"){
            viewModel.dificuldade.value = "medio"
            Log.e("dificuldade", viewModel.dificuldade.value.toString())
        } else if (texto == "dificil"){
            viewModel.dificuldade.value = "dificil"
            Log.e("dificuldade", viewModel.dificuldade.value.toString())
        }
        //verificando tamanho do tabuleiro
        if(texto2 =="padrao"){
            viewModel.tamanho.value = "padrao"
        }
        if(texto2 == "pequeno") {
            viewModel.tamanho.value = "pequeno"
        }

    }

}