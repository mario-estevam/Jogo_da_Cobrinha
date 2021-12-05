package com.example.mariosnake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.ImageView
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*


import com.example.mariosnake.databinding.ActivityTheGameBinding


lateinit var bindingG:ActivityTheGameBinding
lateinit var modelViewGame:ActivityTheGameViewModel
class TheGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        bindingG = DataBindingUtil.setContentView(this, R.layout.activity_the_game)
        modelViewGame = ViewModelProvider (this,).get(ActivityTheGameViewModel::class.java)

        bindingG.gridboard.rowCount = modelViewGame.LINHA
        bindingG.gridboard.columnCount = modelViewGame.COLUNA
        bindingG.modelViewGame = modelViewGame
        bindingG.lifecycleOwner = this

        val param = intent.extras
        val texto = param?.getString("nivel")
        val texto2 = param?.getString("tabuleiro")

        if(texto2.toString()=="padrao"){
            modelViewGame.LINHA = 30
            modelViewGame.COLUNA = 35
        } else if (texto2.toString() == "pequeno"){
            modelViewGame.LINHA = 20
            modelViewGame.COLUNA = 28
        }

        if(texto.toString() == "facil"){
            modelViewGame.speed = 300;
        } else if ( texto.toString() == "medio"){
            modelViewGame.speed = 200;
        } else if ( texto.toString() == "dificil"){
            modelViewGame.speed = 100;
        }


        val inflater = LayoutInflater.from(this)
        for (i in 0 until modelViewGame.LINHA) {
            for (j in 0 until modelViewGame.COLUNA) {
                modelViewGame.boardView[i][j] = inflater.inflate(R.layout.inflate_image_view, bindingG.gridboard, false) as ImageView
                bindingG.gridboard.addView( modelViewGame.boardView[i][j])
            }
        }
        gameRun()
    }

    fun gameRun(){
        Thread{
            while(modelViewGame.running){
                Thread.sleep(modelViewGame.speed)
                runOnUiThread{
                    //limpa tela
                    for (i in 0 until modelViewGame.LINHA) {
                        for (j in 0 until modelViewGame.COLUNA) {
                            modelViewGame.boardView[i][j]!!.setImageResource(R.drawable.black)
                        }
                    }

                    //move peça atual
                    //WHEN (DIRECAO = LEFT)
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
            }
        }.start()
    }
}
