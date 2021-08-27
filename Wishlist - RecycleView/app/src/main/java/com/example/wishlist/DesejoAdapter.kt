package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DesejoAdapter(val lista: ArrayList<Desejo>): RecyclerView.Adapter<DesejoAdapter.DesejoViewHolder>() {

    var listener: OnItemClickRecycleView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesejoViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_items, parent, false)
        return DesejoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DesejoViewHolder, position: Int) {
        val desejo = this.lista.get(position)
        holder.tvNota.text = desejo.nota.toString()
        holder.tvDescricao.text = desejo.descricao
    }

    override fun getItemCount(): Int = this.lista.size

    inner class DesejoViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvNota: TextView
        var tvDescricao: TextView

        init {
            this.tvNota = itemView.findViewById(R.id.tvNota)
            this.tvDescricao = itemView.findViewById(R.id.tvDescricao)

            itemView.setOnClickListener {
                this@DesejoAdapter.listener?.onItemClick(this.adapterPosition)
            }

            itemView.setOnLongClickListener  {
                this@DesejoAdapter.listener?.onItemClick(this.adapterPosition)
                true
            }
        }
    }

}