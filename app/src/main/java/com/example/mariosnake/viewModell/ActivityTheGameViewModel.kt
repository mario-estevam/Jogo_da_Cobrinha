package com.example.mariosnake.viewModell

import android.media.MediaPlayer
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.example.mariosnake.R
import com.example.mariosnake.bindingG
import com.example.mariosnake.model.Cobra
import com.example.mariosnake.model.Fruta
import com.example.mariosnake.model.Tabuleiro



class ActivityTheGameViewModel: ViewModel() {
    var tabuleiro: Tabuleiro = Tabuleiro(30,35)
    var cobra:Cobra = Cobra(14,16,600)
    var fruta: Fruta = Fruta(10,10)
    var running = true

    var direcao = ""
    var texto = ""
    var texto2 = ""
    var flagFt = false;
    var snake = R.drawable.green // é a cobra
    var score1 = "0"
    var score = score1.toInt();
    var run = true
    var estado = false

    fun inicial(){
        bindingG.gridboard.rowCount = tabuleiro.linha
        bindingG.gridboard.columnCount = tabuleiro.coluna
    }

    fun parametros(){
        if(texto2=="padrao"){
            tabuleiro.linha = 30
            tabuleiro.coluna = 35
        } else if (texto2 == "pequeno"){
            tabuleiro.linha = 20
            tabuleiro.coluna = 35
        }



        if(texto == "facil"){
            cobra.speed = 600;
            Log.e("velocidade", cobra.speed.toString())
        } else if ( texto == "medio"){
            cobra.speed = 200;
            Log.e("velocidade",cobra.speed.toString())
        } else if ( texto == "dificil"){
            cobra.speed = 100;
            Log.e("velocidade", cobra.speed.toString())
        } else if(texto == "mtd"){
            cobra.speed = 50
        }

    }

    fun thread(){
        //limpa tela
        for (i in 0 until tabuleiro.linha) {
            for (j in 0 until tabuleiro.coluna) {
                boardView[i][j]!!.setImageResource(R.drawable.black)
            }
        }
        if(direcao == "cima"){
            cobra.moveUp()
        } else if (direcao == "baixo"){
            cobra.moveDown()
        } else if(direcao == "direita"){
            cobra.moveRight()
        } else if (direcao == "esquerda"){
            cobra.moveLeft()
        }

        if(!flagFt){
            fruta.position()
        }

        if((fruta.x == cobra.x) && (fruta.y == cobra.y) ){
            fruta.randon()
            flagFt = true
            score++
            score1=score.toString()
            boardView[cobra.x][cobra.y]!!.setImageResource(snake)
        }
        if((cobra.x == 30)||(cobra.y==35)){
            run = false
            running = false
        }


        try {
            boardView[fruta.x][fruta.y]!!.setImageResource(R.drawable.white)
            boardView[cobra.x][cobra.y]!!.setImageResource(snake)
        }catch (e:ArrayIndexOutOfBoundsException ) {
            running = false
        }
    }
//
//    fun somft(){
//        val mediaPlayer:MediaPlayer = MediaPlayer.create(this @ActivityTheGameViewModel,R.raw.eat)
//        mediaPlayer.start()
//    }


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



    var boardView = Array(tabuleiro.linha){
        arrayOfNulls<ImageView>(tabuleiro.coluna)
    }
}