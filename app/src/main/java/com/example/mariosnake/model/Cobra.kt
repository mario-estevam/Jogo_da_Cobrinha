package com.example.mariosnake.model

import com.example.mariosnake.R


data class Cobra(var x:Int, var y:Int, var speed:Long ) {

    init {
        speed = 600
    }
    var listPosicaoCobra = mutableListOf<Ponto>()


        fun moveDown(){
            for (i in 0 until listPosicaoCobra.size) {
                listPosicaoCobra[i].x = listPosicaoCobra[i].x + 1
            }
        }

        fun moveUp(){
            for (i in 0 until listPosicaoCobra.size) {
                listPosicaoCobra[i].x = listPosicaoCobra[i].x - 1
            }
        }

        fun moveLeft(){
            for (i in 0 until listPosicaoCobra.size) {
                listPosicaoCobra[i].y = listPosicaoCobra[i].y - 1
            }
        }

        fun moveRight(){
            for (i in 0 until listPosicaoCobra.size) {
                listPosicaoCobra[i].y = listPosicaoCobra[i].y + 1
            }

        }


}