package com.example.aplicacoescomlistas

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FilmesActivity : AppCompatActivity() {

    private val listaFilmes =
        mutableListOf<Filme>()

    private lateinit var adapter:
            FilmeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_filmes)


        // PROTEÇÃO CONTRA CÂMERA / NOTCH

        val root =
            findViewById<View>(R.id.rootFilmes)

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


        // COMPONENTES

        val btnVoltar =
            findViewById<Button>(
                R.id.btnVoltarFilmes
            )

        val edtTitulo =
            findViewById<EditText>(
                R.id.edtTitulo
            )

        val edtDiretor =
            findViewById<EditText>(
                R.id.edtDiretor
            )

        val btnAdicionar =
            findViewById<Button>(
                R.id.btnAdicionarFilme
            )

        val recyclerFilmes =
            findViewById<RecyclerView>(
                R.id.recyclerFilmes
            )


        // BOTÃO VOLTAR

        btnVoltar.setOnClickListener {
            finish()
        }


        // RECYCLERVIEW

        adapter =
            FilmeAdapter(listaFilmes)

        recyclerFilmes.layoutManager =
            GridLayoutManager(this, 2)

        recyclerFilmes.adapter =
            adapter


        // ADICIONAR FILME

        btnAdicionar.setOnClickListener {

            val titulo =
                edtTitulo.text
                    .toString()
                    .trim()

            val diretor =
                edtDiretor.text
                    .toString()
                    .trim()


            if (
                titulo.isEmpty() ||
                diretor.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Preencha o título e o diretor.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }


            val novoFilme =
                Filme(
                    titulo,
                    diretor
                )


            listaFilmes.add(
                novoFilme
            )


            adapter.notifyItemInserted(
                listaFilmes.size - 1
            )


            edtTitulo.text.clear()
            edtDiretor.text.clear()


            Toast.makeText(
                this,
                "Filme adicionado!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}