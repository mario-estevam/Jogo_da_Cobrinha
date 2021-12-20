package com.example.mariosnake.model

import com.example.mariosnake.R


data class Cobra(var x:Int, var y:Int, var speed:Long ) {

    init {
        speed = 600
    }

    var ponto: Ponto = Ponto(1, 20)
    var xc = mutableListOf(1, 20)
    var snake = mutableListOf(mutableListOf(1, 20))

    fun restartGame(){

        ponto= Ponto(1,20)
        xc = mutableListOf(1,20)
        snake = mutableListOf(mutableListOf(1,20))

    }

    fun aumentarCobra() {

        ponto = Ponto(xc[0], xc[1])
        snake.add(0, mutableListOf(ponto.cobraLinha, ponto.cobraColuna))

    }

    fun removerCauda() {
        snake.removeLast()
    }

    fun moveDown() {
        xc[0]++
    }

    fun moveUp() {
        xc[0]--
    }

    fun moveLeft(){
        xc[1]--
    }

    fun moveRight(){
        xc[1]++
    }


}