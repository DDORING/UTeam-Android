package com.neordi6th.ddoring

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neordi6th.ddoring.databinding.ItemRcviewLoginBinding

class CharacterAdapter(private val dataSet:ArrayList<CharacterData>) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {


    class ViewHolder(private val binding: ItemRcviewLoginBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(item: CharacterData){
            binding.message.text = item.name
            binding.message2.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRcviewLoginBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }
}