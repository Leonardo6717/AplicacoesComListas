package com.example.aplicacoescomlistas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val root = findViewById<View>(R.id.rootMain)

        val paddingLeft = root.paddingLeft
        val paddingTop = root.paddingTop
        val paddingRight = root.paddingRight
        val paddingBottom = root.paddingBottom

        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->

            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or
                        WindowInsetsCompat.Type.displayCutout()
            )

            view.setPadding(
                paddingLeft,
                paddingTop + bars.top,
                paddingRight,
                paddingBottom + bars.bottom
            )

            insets
        }


        val btnFilmes =
            findViewById<Button>(R.id.btnFilmes)

        val btnTarefas =
            findViewById<Button>(R.id.btnTarefas)


        btnFilmes.setOnClickListener {

            val intent =
                Intent(this, FilmesActivity::class.java)

            startActivity(intent)
        }


        btnTarefas.setOnClickListener {

            val intent =
                Intent(this, TarefasActivity::class.java)

            startActivity(intent)
        }
    }
}