package com.example.mariosnake.viewModell


import androidx.lifecycle.ViewModel
import com.example.mariosnake.bindingC

class ActivityConfigViewModel:ViewModel() {

    var dificuldade = ""
    var tabuleiroB = ""

    fun recebe(){
        if(dificuldade== "facil"){
            bindingC.facil.isChecked =  true
        } else if ( dificuldade == "medio"){
            bindingC.medio.isChecked = true
        } else if ( dificuldade == "dificil"){
            bindingC.dificil.isChecked = true
        } else if(dificuldade == "mtd"){
            bindingC.mtdificil.isChecked = true
        }

        if(tabuleiroB=="padrao"){
            bindingC.padrao.isChecked = true
        } else if (tabuleiroB == "pequeno"){
            bindingC.peq.isChecked = true
        }
    }



}