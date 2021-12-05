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
    var texto = ""
    var texto2 = ""

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

    fun parametros(){
        if(texto2=="padrao"){
            modelViewGame.LINHA = 30
            modelViewGame.COLUNA = 35
        } else if (texto2 == "pequeno"){
            modelViewGame.LINHA = 20
            modelViewGame.COLUNA = 28
        }

        if(texto == "facil"){
            modelViewGame.speed = 400;
            Log.e("velocidade", modelViewGame.speed.toString())
        } else if ( texto == "medio"){
            modelViewGame.speed = 200;
            Log.e("velocidade", modelViewGame.speed.toString())
        } else if ( texto == "dificil"){
            modelViewGame.speed = 100;
            Log.e("velocidade", modelViewGame.speed.toString())
        }
    }

    fun Thread(){
        //limpa tela
        for (i in 0 until modelViewGame.LINHA) {
            for (j in 0 until modelViewGame.COLUNA) {
                modelViewGame.boardView[i][j]!!.setImageResource(R.drawable.black)
            }
        }
        if(modelViewGame.direcao == "cima"){
            modelViewGame.pt.moveUp()
        } else if (modelViewGame.direcao == "baixo"){
            modelViewGame.pt.moveDown()
        } else if(modelViewGame.direcao == "direita"){
            modelViewGame.pt.moveLeft()
        } else if (modelViewGame.direcao == "esquerda"){
            modelViewGame.pt.moveRight()
        }


        try {
            modelViewGame.boardView[modelViewGame.pt.x][modelViewGame.pt.y]!!.setImageResource(R.drawable.green)
        }catch (e:ArrayIndexOutOfBoundsException ) {
            //se a peça passou das bordas eu vou parar o jogo
            modelViewGame.running = false
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