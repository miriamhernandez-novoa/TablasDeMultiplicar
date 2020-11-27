package com.example.tablascool

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_aprende.*
import kotlinx.android.synthetic.main.activity_practica.*
import kotlin.random.Random

class PracticaActivity : AppCompatActivity() {
    var resultado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practica)

        generaOperacion()

        btnCalificar.setOnClickListener {
            val resText = edNumeros.text.toString()
            if (!resText.equals("")) {
                val respuesta = resText.toInt()
                if (respuesta == resultado) {
                    crearDialogoOK()
                } else {
                    crearDialogoNo()
                }
            }
            generaOperacion()
        }

        btnRegresarInicio.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)

        }

    }


    fun crearDialogoOK(){
        val miDialogView = LayoutInflater.from(this).inflate(R.layout.dialogook,null)
        val mBuilder = AlertDialog.Builder(this).setView(miDialogView)
            .setTitle("¡CORRECTO!")
        val miDialogOk = mBuilder.create()
        miDialogOk.show()
        val miPlayer:MediaPlayer = MediaPlayer.create(this,R.raw.aplausos)
        miPlayer.start()
    }

    fun crearDialogoNo(){
        val miDialogView = LayoutInflater.from(this).inflate(R.layout.dialogono,null)
        val mBuilder = AlertDialog.Builder(this).setView(miDialogView)
            .setTitle("¡INCORRECTO!")
        val miDialogOk = mBuilder.create()
        miDialogOk.show()
        val miPlayer:MediaPlayer = MediaPlayer.create(this,R.raw.error)
        miPlayer.start()
    }
    fun generaOperacion(){
        val operando1 = Random.nextInt(1,10)
        val operando2 = Random.nextInt(1,10)

        resultado = operando1*operando2
        tvMultiplicacion.text = "$operando1 x $operando2 = ?"
        edNumeros.text.clear()

    }


}