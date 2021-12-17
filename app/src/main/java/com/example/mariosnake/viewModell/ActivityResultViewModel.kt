package com.example.mariosnake.viewModell

import android.util.Log
import androidx.lifecycle.ViewModel


class ActivityResultViewModel: ViewModel() {
    var pontos:Int = 0
    var recorde:Int = 0
    var ultimo:Int = 0;

   fun verifica(){
       if(pontos > recorde){
           recorde = pontos
           Log.e("novo rec", recorde.toString())
       }

   }

}