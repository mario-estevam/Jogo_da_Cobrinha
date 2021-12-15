package com.example.mariosnake.viewModell

import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.example.mariosnake.R
import com.example.mariosnake.bindingG


class ActivityTheGameViewModel: ViewModel() {
    var LINHA = 30
    var COLUNA = 35
    var running = true
    var speed:Long = 300
    var direcao = ""
    var texto = ""
    var texto2 = ""
    var flagFt = false;
    var snake = R.drawable.green
    var pt = Ponto(14,16)
    var ft = Fruta(7,7)
    var score1 = "0"
    var score = score1.toInt();
    var run = true

    inner class Fruta(var x:Int, var y:Int){


        fun position(){
            x=10;
            y =10;
        }

        fun randon(){
            x = (0..20).random()
            y = (0..25).random()
        }


    }


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


    fun inicial(){
        bindingG.gridboard.rowCount = LINHA
        bindingG.gridboard.columnCount = COLUNA
    }

    fun parametros(){
        if(texto2=="padrao"){
            LINHA = 30
           COLUNA = 35
        } else if (texto2 == "pequeno"){
           LINHA = 20
            COLUNA = 28
        }

        if(texto == "facil"){
            speed = 600;
            Log.e("velocidade", speed.toString())
        } else if ( texto == "medio"){
            speed = 200;
            Log.e("velocidade", speed.toString())
        } else if ( texto == "dificil"){
            speed = 100;
            Log.e("velocidade", speed.toString())
        }

    }

    fun thread(){
        //limpa tela
        for (i in 0 until LINHA) {
            for (j in 0 until COLUNA) {
                boardView[i][j]!!.setImageResource(R.drawable.black)
            }
        }
        if(direcao == "cima"){
            pt.moveUp()
        } else if (direcao == "baixo"){
            pt.moveDown()
        } else if(direcao == "direita"){
            pt.moveLeft()
        } else if (direcao == "esquerda"){
            pt.moveRight()
        }

       if(!flagFt){
          ft.position()
       }

        if((ft.x == pt.x) && (ft.y == pt.y) ){
            ft.randon()
            flagFt = true
            score++
            score1=score.toString()
            //boardView[pt.x++][pt.y++]!!.setImageResource(snake)
        }
        if((pt.x == 30)||(pt.y==35)){
            run = false
            running = false
        }



        try {
            boardView[ft.x][ft.y]!!.setImageResource(R.drawable.white)
            boardView[pt.x][pt.y]!!.setImageResource(snake)
        }catch (e:ArrayIndexOutOfBoundsException ) {
            Log.e("entrou", "entrouuu")
            //se a peça passou das bordas eu vou parar o jogo
           running = false
            Log.e("running", running.toString())

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