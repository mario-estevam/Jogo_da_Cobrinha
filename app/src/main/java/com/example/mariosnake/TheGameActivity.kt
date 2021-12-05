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
        modelViewGame.texto = param?.getString("nivel").toString()
        modelViewGame.texto2 = param?.getString("tabuleiro").toString()
        modelViewGame.parametros()

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
                    modelViewGame.Thread()
                }
            }
        }.start()
    }
}
