package com.example.mariosnake.viewModell

import android.media.MediaPlayer
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.example.mariosnake.R
import com.example.mariosnake.bindingG
import com.example.mariosnake.model.Cobra
import com.example.mariosnake.model.Fruta
import com.example.mariosnake.model.Ponto
import com.example.mariosnake.model.Tabuleiro
import com.example.mariosnake.modelViewGame


class ActivityTheGameViewModel: ViewModel() {
    var tabuleiro: Tabuleiro = Tabuleiro(30,35)
    var cobra:Cobra = Cobra(14,16,600)
    var fruta: Fruta = Fruta(15,16)
    var ponto:Ponto = Ponto(14,16)
    var running = true
    var direcao = ""
    var dificuldade = ""
    var tmTab = ""
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


        if(tmTab=="padrao"){
            tabuleiro.linha = 30
            tabuleiro.coluna = 35
        } else if (tmTab == "pequeno"){
            tabuleiro.linha = 20
            tabuleiro.coluna = 35
        }

        when (dificuldade) {
            "facil" -> {
                cobra.speed = 600;
                Log.e("velocidade", cobra.speed.toString())
            }
            "medio" -> {
                cobra.speed = 200;
                Log.e("velocidade",cobra.speed.toString())
            }
            "dificil" -> {
                cobra.speed = 100;
                Log.e("velocidade", cobra.speed.toString())
            }
            "mtd" -> {
                cobra.speed = 50
            }
        }

    }

    fun thread(){


        for (i in 0 until tabuleiro.linha) {
            for (j in 0 until tabuleiro.coluna) {
                boardView[i][j]!!.setImageResource(R.drawable.black)
            }
        }

        if (direcao == "cima" ){
            cobra.moveUp()
        } else if (direcao == "baixo" ){
            cobra.moveDown()
        } else if(direcao == "direita"){
            cobra.moveRight()
        } else if (direcao == "esquerda"){
            cobra.moveLeft()
        }

        if(!flagFt){
            fruta.position()
        }

        cobra.aumentarCobra()

        if((fruta.x == cobra.xc[0]) && (fruta.y == cobra.xc[1]) ){
            fruta.randon()
            flagFt = true
            score++;
            score1 = score.toString()

        }else{
            cobra.removerCauda()
        }

        if((cobra.xc[0] == 41)||(cobra.xc[1]==41)){
            running = false
        }

            boardView[fruta.x][fruta.y]!!.setImageResource(R.drawable.white)
            for (i in 0 until cobra.snake.size){
                boardView[cobra.snake[i][0]][cobra.snake[i][1]]!!.setImageResource(R.drawable.green)
            }

            //boardView[cobra.linha][cobra.coluna]!!.setImageResource(R.drawable.white)



    }

//    fun limpaTela(){
//        //limpa tela
//        for (i in 0 until tabuleiro.linha) {
//            for (j in 0 until tabuleiro.coluna) {
//                boardView[i][j]!!.setImageResource(R.drawable.black)
//            }
//        }
//    }
//
//    fun moveCobra(){
//       when(direcao){
//           "cima" -> {
//               cobra.moveUp()
//           }
//           "baixo" -> {
//               cobra.moveDown()
//           }
//           "esquerda" -> {
//               cobra.moveLeft()
//           }
//           "direita" -> {
//               cobra.moveRight()
//           }
//        }
//    }
//
//    fun printCobra() {
//        for (i in 0 until cobra.listPosicaoCobra.size) {
//            boardView[cobra.listPosicaoCobra[i].x][cobra.listPosicaoCobra[i].y]!!.setImageResource(R.drawable.green)
//        }
//    }
//
//
//
//fun frutaFun(){
//    if(!flagFt){
//        fruta.position()
//    }
//
//    if((fruta.x == cobra.listPosicaoCobra[0].x) && (fruta.y == cobra.listPosicaoCobra[0].y) ){
//        fruta.randon()
//        flagFt = true
//        score++
//        score1=score.toString()
//        cobra.listPosicaoCobra.add(Ponto(cobra.listPosicaoCobra[0].x, cobra.listPosicaoCobra[0].y++))
//    }
//
//    boardView[fruta.x][fruta.y]!!.setImageResource(R.drawable.white)
//
//}

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


