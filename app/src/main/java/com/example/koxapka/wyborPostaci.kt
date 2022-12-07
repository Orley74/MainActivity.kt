package com.example.koxapka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class wyborPostaci : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wybor_postaci)
        val lowca = findViewById(R.id.LowcaId) as ImageButton
        val mag = findViewById(R.id.MagId) as ImageButton
        val wojownik = findViewById(R.id.WojownikId) as ImageButton
        val nick = findViewById(R.id.nickname) as EditText
        var Playernick= nick.getText().toString()
        lowca.setOnClickListener {
            val intent = Intent(this,Lowca_Game::class.java)
            intent.putExtra("nick",nick.getText().toString())
            startActivity(intent)

        }
        wojownik.setOnClickListener {
            val intent = Intent(this,Wojownik_Game::class.java)
            intent.putExtra("nick",nick.getText().toString())
            startActivity(intent)

        }
        mag.setOnClickListener {
            val intent = Intent(this,Mag_Game::class.java)
            intent.putExtra("nick",nick.getText().toString())
            startActivity(intent)

        }

    }

    override fun onStop() {
        super.onStop()
        finish()
    }

}


