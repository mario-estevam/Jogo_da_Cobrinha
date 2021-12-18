package com.example.mariosnake

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.ImageView
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*


import com.example.mariosnake.databinding.ActivityTheGameBinding
import com.example.mariosnake.model.Cobra
import com.example.mariosnake.model.Ponto
import com.example.mariosnake.viewModell.ActivityTheGameViewModel
import java.lang.Exception


lateinit var bindingG:ActivityTheGameBinding
lateinit var modelViewGame: ActivityTheGameViewModel
var cont = 0


class TheGameActivity : AppCompatActivity() {
    val activityResultt = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        when(it.resultCode){
            RESULT_OK ->{
                cont = 0
                modelViewGame.fruta.x = 10
                modelViewGame.fruta.y = 10
                modelViewGame.cobra.listPosicaoCobra.clear()
                modelViewGame.cobra.listPosicaoCobra.add(Ponto(15, 15))
                modelViewGame.score1 = "0"
                modelViewGame.score = 0
                modelViewGame.running = true
                gameRun()
            }
            RESULT_CANCELED ->{
                binding.continuar.visibility = View.GONE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        bindingG = DataBindingUtil.setContentView(this, R.layout.activity_the_game)
        modelViewGame = ViewModelProvider (this,).get(ActivityTheGameViewModel::class.java)

        cont = 0
        bindingG.modelViewGame = modelViewGame
        bindingG.lifecycleOwner = this
        modelViewGame.inicial()

        val param = intent.extras
        modelViewGame.dificuldade = param?.getString("nivel").toString()
        modelViewGame.tmTab = param?.getString("tabuleiro").toString()
        modelViewGame.estado = param?.getBoolean("estd",false) == true
//        if(modelViewGame.estado){
//            restore()
//        }
        param?.getString("pts", "0").toString().also { modelViewGame.score1 = it }

        var sc =  param?.getString("pts").toString()
        modelViewGame.score =  sc.toInt()

        modelViewGame.parametros()

        val inflater = LayoutInflater.from(this)
        for (i in 0 until modelViewGame.tabuleiro.linha) {
            for (j in 0 until modelViewGame.tabuleiro.coluna) {
                modelViewGame.boardView[i][j] = inflater.inflate(R.layout.inflate_image_view, bindingG.gridboard, false) as ImageView
                bindingG.gridboard.addView( modelViewGame.boardView[i][j])
            }
        }
        if(!modelViewGame.running){
            val intentt = Intent(this,ActivityResultado::class.java)
            val parametro = Bundle()
            val send = modelViewGame.score1
            parametro.putString("pts", send)
            intentt.putExtras(parametro)
            activityResultt.launch(intentt)
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

    }

    fun gameRun(){

        Thread{
                while(modelViewGame.running){
                    Thread.sleep(modelViewGame.cobra.speed)
                    runOnUiThread{
                        try {
                                modelViewGame.limpaTela()
                                modelViewGame.moveCobra()
                                modelViewGame.printCobra()
                                modelViewGame.frutaFun()
                                bindingG.pontos.text = modelViewGame.score1

                        }catch (e:Exception){

                            Log.e("excpetion",cont.toString())
                            if(cont == 2){
                                    val intentt = Intent(this,ActivityResultado::class.java)
                                    val parametro = Bundle()
                                    val send = modelViewGame.score1
                                    parametro.putString("pts", send)
                                    intentt.putExtras(parametro)
                                    activityResultt.launch(intentt)
                            }
                            cont++
                        }

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

    override fun onDestroy() {
        super.onDestroy()
        viewModel.flagButton = "true"
    }



}
