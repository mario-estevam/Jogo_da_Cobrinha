package com.example.mariosnake

import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModel


class ActivityTheGameViewModel: ViewModel() {
    var LINHA = 30
    var COLUNA = 35
    var running = true
    var speed:Long = 300
    var direcao = ""




    var pt = Ponto(14,16)

    inner class Ponto(var x:Int,var y:Int){
        fun moveDown(){
            x++
        }
        fun moveUp(){
            x--
        }
        fun moveLeft(){
            y++
        }
        fun moveRight(){
            y--
        }
    }


    fun direcaoCima(){
        direcao = "cima"
    }
    fun direcaoBaixo(){
        direcao = "baixo"
    }
    fun direcaoDireita(){
        direcao = "direita"
    }
    fun direcaoEsquerda(){
        direcao = "esquerda"
    }

    //val board = Array(LINHA, { IntArray(COLUNA) })

    var board = Array(LINHA) {
        Array(COLUNA){0}
    }

    var boardView = Array(LINHA){
        arrayOfNulls<ImageView>(COLUNA)
    }
}