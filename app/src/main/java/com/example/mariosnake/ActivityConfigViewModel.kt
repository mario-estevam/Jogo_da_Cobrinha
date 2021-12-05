package com.example.mariosnake


import androidx.lifecycle.ViewModel

class ActivityConfigViewModel:ViewModel() {

    var texto = ""
    var texto2 = ""

    fun recebe(){
        if(texto == "facil"){
            bindingC.facil.isChecked =  true
        } else if ( texto == "medio"){
            bindingC.medio.isChecked = true
        } else if ( texto.toString() == "dificil"){
            bindingC.dificil.isChecked = true
        }

        if(texto2=="padrao"){
            bindingC.padrao.isChecked = true
        } else if (texto2 == "pequeno"){
            bindingC.peq.isChecked = true
        }
    }



}