package com.android.personal_project_apple_market.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.personal_project_apple_market.DetailActivity
import com.android.personal_project_apple_market.databinding.RecyclerItemBinding
import com.android.personal_project_apple_market.model.Apple

class RecyclerViewAdapter(val list: MutableList<Apple>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount()  = list.size

    class ViewHolder(val binding: RecyclerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        lateinit var currentItem: Apple
        init{
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra("item", currentItem)
                binding.root.context.startActivity(intent)
            }
        }

        fun bind(item: Apple) {
            currentItem = item
            binding.ivPicture.setImageResource(item.picture)
            binding.tvTitle.text = item.title
            binding.tvAddress.text = item.address
            binding.tvPrice.text = item.price
            binding.tvReply.text = item.reply
            binding.tvLike.text = item.like

        }
    }
}