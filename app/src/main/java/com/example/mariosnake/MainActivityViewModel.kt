package com.example.mariosnake

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivityViewModel: ViewModel() {

    var dificuldade = MutableLiveData<String>("facil")
    var tamanho = MutableLiveData<String>("padrao")
    var flagButton = false // flag passada pra activity the game que verifica se o jogo já foi inciado para ativar o botão continuar

}