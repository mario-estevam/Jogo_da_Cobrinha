package com.example.mariosnake

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.ImageView
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*


import com.example.mariosnake.databinding.ActivityTheGameBinding
import com.example.mariosnake.viewModell.ActivityTheGameViewModel


lateinit var bindingG:ActivityTheGameBinding
lateinit var modelViewGame: ActivityTheGameViewModel


class TheGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        bindingG = DataBindingUtil.setContentView(this, R.layout.activity_the_game)
        modelViewGame = ViewModelProvider (this,).get(ActivityTheGameViewModel::class.java)

        val activityResultt = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
              RESULT_OK ->{
                  modelViewGame.pt.x = 14
                  modelViewGame.pt.y = 16
                  modelViewGame.ft.x = 7
                  modelViewGame.ft.y = 7
                  modelViewGame.score1 = "0"
                  modelViewGame.score = 0
                  gameRun()
                }
                RESULT_CANCELED ->{

                }
            }
        }

        bindingG.modelViewGame = modelViewGame
        bindingG.lifecycleOwner = this
        modelViewGame.inicial()

        val param = intent.extras
        modelViewGame.texto = param?.getString("nivel").toString()
        modelViewGame.texto2 = param?.getString("tabuleiro").toString()
        param?.getString("pts", "0").toString().also { modelViewGame.score1 = it }
        modelViewGame.parametros()

        val inflater = LayoutInflater.from(this)
        for (i in 0 until modelViewGame.LINHA) {
            for (j in 0 until modelViewGame.COLUNA) {
                modelViewGame.boardView[i][j] = inflater.inflate(R.layout.inflate_image_view, bindingG.gridboard, false) as ImageView
                bindingG.gridboard.addView( modelViewGame.boardView[i][j])
            }
        }
        gameRun()


        bindingG.parar.setOnClickListener{
            val pts = modelViewGame.score
            val intent = Intent()
            val parameter = Bundle()
            parameter.putString("pontos", pts.toString() )
            parameter.putString("flagButton", "true")
            intent.putExtras(parameter)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        bindingG.stoped.setOnClickListener{
            val intentt = Intent(this,ActivityResultado::class.java)
            val parametro = Bundle()
            var send = modelViewGame.score1
            parametro.putString("pts", send)
            intentt.putExtras(parametro)
            activityResultt.launch(intentt)
        }
    }

    fun gameRun(){
        Thread{
                while(modelViewGame.running){
                    Thread.sleep(modelViewGame.speed)
                    runOnUiThread{
                        modelViewGame.thread()
                        bindingG.pontos.text = modelViewGame.score1
                    }
                }
        }.start()

    }


    override fun onStop() {
        super.onStop()
        modelViewGame.running = false
    }

    override fun onResume() {
        super.onResume()
        modelViewGame.running = true
        gameRun()
    }




}
