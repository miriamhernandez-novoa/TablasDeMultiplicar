package com.example.tablascool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAprender.setOnClickListener {
            val i = Intent(this, AprendeActivity::class.java)
            startActivity(i)
        }

        btnPracticar.setOnClickListener{
            val i = Intent(this, PracticaActivity::class.java)
            startActivity(i)

        }

    }
}