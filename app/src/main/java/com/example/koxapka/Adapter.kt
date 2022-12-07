package com.example.koxapka

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class Adapter(var lista : ArrayList<data>): RecyclerView.Adapter<Adapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.recycler,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=lista[position]
        holder.PlayerAttack.text=currentItem.opis
       // holder.EnemyAttack.text="${currentItem.name} uasd z sila + ${currentItem.attack}\n Przeciwnik oasd  c jachnika"

    }

    override fun getItemCount(): Int {
        return lista.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val PlayerAttack : TextView = itemView.findViewById(R.id.PlayerAttack)



    }
}