package com.example.mariosnake.model

import com.example.mariosnake.R

data class Cobra(var x:Int, var y:Int, var speed:Long ) {

    init {
        x = 14
        y = 16
        speed = 600
    }

    fun cb(){
        var cob = R.drawable.green
    }

        fun moveDown(){
            x++
        }
        fun moveUp(){
            x--
        }
        fun moveLeft(){
            y--
        }
        fun moveRight(){
            y++
        }


}