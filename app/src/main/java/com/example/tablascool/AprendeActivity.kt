package com.example.tablascool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_aprende.*
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class AprendeActivity : AppCompatActivity(), TextToSpeech.OnInitListener, AdapterView.OnItemClickListener {
    //Variables globales
    var tts: TextToSpeech? = null
    var listaElementos: MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aprende)

        listaElementos = mutableListOf<String>() //este es el tipo de datos correcto
        tts = TextToSpeech(this,this)
        Log.i("lenguajes", Locale.getAvailableLocales().toString())

        listaTablas.setOnItemClickListener(this)

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                listaElementos!!.clear()//aquí limpio la lista
                if (p1 < 1) {
                    seekBar.setProgress(1)
                }
                if (p1 > 0){
                    for(i in 1..10){
                        val texto = "$p1 x $i = ${p1*i}"
                        listaElementos!!.add(texto)//aquí añado el nuevo elemento
                    }
                }

                val adaptador = ArrayAdapter(application,android.R.layout.simple_list_item_1,listaElementos!!)
                listaTablas.adapter = adaptador
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        btnInicio.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)

        }

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale("es_MX"))
            if (result != TextToSpeech.LANG_NOT_SUPPORTED && result != TextToSpeech.LANG_MISSING_DATA){
                Toast.makeText(this,"",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Lenguaje no soportado", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
       if (tts!! != null) {
           tts!!.stop()
           tts!!.shutdown()
       }
        super.onDestroy()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val textoALeer = listaElementos!!.get(position)
        tts!!.speak(textoALeer,TextToSpeech.QUEUE_FLUSH,null,null)
    }
}


