package com.example.aplicacoescomlistas

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TarefasActivity : AppCompatActivity() {

    private val listaTarefas =
        mutableListOf<Tarefa>()

    private lateinit var adapter:
            TarefaAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tarefas)


        // PROTEÇÃO CONTRA CÂMERA / NOTCH

        val root =
            findViewById<View>(
                R.id.rootTarefas
            )

        val paddingLeft =
            root.paddingLeft

        val paddingTop =
            root.paddingTop

        val paddingRight =
            root.paddingRight

        val paddingBottom =
            root.paddingBottom


        ViewCompat.setOnApplyWindowInsetsListener(
            root
        ) { view, insets ->

            val bars =
                insets.getInsets(
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
                R.id.btnVoltarTarefas
            )

        val edtNome =
            findViewById<EditText>(
                R.id.edtNomeTarefa
            )

        val edtDescricao =
            findViewById<EditText>(
                R.id.edtDescricaoTarefa
            )

        val btnAdicionar =
            findViewById<Button>(
                R.id.btnAdicionarTarefa
            )

        val recyclerTarefas =
            findViewById<RecyclerView>(
                R.id.recyclerTarefas
            )


        // VOLTAR

        btnVoltar.setOnClickListener {
            finish()
        }


        // RECYCLERVIEW

        adapter =
            TarefaAdapter(listaTarefas)


        recyclerTarefas.layoutManager =
            LinearLayoutManager(this)


        recyclerTarefas.adapter =
            adapter


        // ADICIONAR TAREFA

        btnAdicionar.setOnClickListener {

            val nome =
                edtNome.text
                    .toString()
                    .trim()

            val descricao =
                edtDescricao.text
                    .toString()
                    .trim()


            if (
                nome.isEmpty() ||
                descricao.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Preencha todos os campos.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }


            val novaTarefa =
                Tarefa(
                    nome,
                    descricao
                )


            listaTarefas.add(
                novaTarefa
            )


            adapter.notifyItemInserted(
                listaTarefas.size - 1
            )


            edtNome.text.clear()
            edtDescricao.text.clear()


            Toast.makeText(
                this,
                "Tarefa adicionada!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}