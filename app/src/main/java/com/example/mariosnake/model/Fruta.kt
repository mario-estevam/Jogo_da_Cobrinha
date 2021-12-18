package com.example.mariosnake.model

data class Fruta(var x:Int, var y:Int ) {

    init{
        x = 14
        y = 15
    }

    fun position(){
        x=14;
        y =15;
    }

    fun randon(){
        x = (0..20).random()
        y = (0..25).random()
    }
}