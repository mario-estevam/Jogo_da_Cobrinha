package com.example.mariosnake

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mariosnake.databinding.ActivityResultadoBinding
import com.example.mariosnake.viewModell.ActivityResultViewModel
import com.example.mariosnake.viewModell.MainActivityViewModel
import java.lang.System.exit


class ActivityResultado : AppCompatActivity() {
    lateinit var bindingA: ActivityResultadoBinding
    lateinit var viewModelResultado: ActivityResultViewModel
    var rec:Int = 0
    var last:Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingA = DataBindingUtil.setContentView(this, R.layout.activity_resultado)
        viewModelResultado = ViewModelProvider(this,)[ActivityResultViewModel::class.java]
        val param = intent.extras
        val texto = param?.getString("pts").toString()
        var pontos = texto.toInt()
        val preferences = getSharedPreferences("MY_RESULT", MODE_PRIVATE)
        viewModelResultado.recorde  = preferences.getInt("recorde",0)
        viewModelResultado.ultimo  = preferences.getInt("ultimaPontuacao",0)
        bindingA.last2.text = viewModelResultado.ultimo.toString()


        viewModelResultado.pontos = pontos


        bindingA.result.text = viewModelResultado.pontos.toString()
        viewModelResultado.verifica()

        bindingA.recorde.text =  viewModelResultado.recorde.toString()


        rec =  viewModelResultado.recorde
        last = viewModelResultado.pontos

        bindingA.novo.setOnClickListener{
            val intent = Intent()
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        bindingA.sair.setOnClickListener{
            this.finishAffinity();
        }

    }

    override fun onStop() {
        super.onStop()

        val preferences = getSharedPreferences("MY_RESULT", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt("recorde", rec)
        editor.putInt("ultimaPontuacao", last)
        Log.e("recorde", rec.toString())
        editor.apply()
    }


}
