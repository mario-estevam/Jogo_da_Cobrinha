package com.example.mariosnake.model

data class Fruta(var x:Int, var y:Int ) {

    init{
        x = 10
        y = 10
    }

    fun position(){
        x=10;
        y =10;
    }

    fun randon(){
        x = (0..20).random()
        y = (0..25).random()
    }
}