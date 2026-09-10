package com.example.aplicacoescomlistas

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TarefaAdapter(
    private val tarefas: MutableList<Tarefa>
) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {


    class TarefaViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val txtNome: TextView =
            itemView.findViewById(R.id.txtNomeTarefa)

        val txtDescricao: TextView =
            itemView.findViewById(R.id.txtDescricaoTarefa)

        val btnConcluir: Button =
            itemView.findViewById(R.id.btnConcluir)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TarefaViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_tarefa,
                parent,
                false
            )

        return TarefaViewHolder(view)
    }


    override fun onBindViewHolder(
        holder: TarefaViewHolder,
        position: Int
    ) {

        val tarefa = tarefas[position]


        holder.txtNome.text = tarefa.nome

        holder.txtDescricao.text = tarefa.descricao


        if (tarefa.concluida) {

            holder.btnConcluir.text = "Concluída ✓"

            holder.btnConcluir.isEnabled = false

            holder.txtNome.paintFlags =
                holder.txtNome.paintFlags or
                        Paint.STRIKE_THRU_TEXT_FLAG

        } else {

            holder.btnConcluir.text = "Concluir"

            holder.btnConcluir.isEnabled = true

            holder.txtNome.paintFlags =
                holder.txtNome.paintFlags and
                        Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }


        holder.btnConcluir.setOnClickListener {

            tarefa.concluida = true

            notifyItemChanged(position)
        }
    }


    override fun getItemCount(): Int {

        return tarefas.size
    }
}