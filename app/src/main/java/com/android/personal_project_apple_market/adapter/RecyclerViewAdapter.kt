package com.android.personal_project_apple_market.adapter

import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.personal_project_apple_market.DetailActivity
import com.android.personal_project_apple_market.MainActivity
import com.android.personal_project_apple_market.R
import com.android.personal_project_apple_market.databinding.RecyclerItemBinding
import com.android.personal_project_apple_market.model.Apple

class RecyclerViewAdapter(val list: MutableList<Apple>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount() = list.size


    inner class ViewHolder(
        val binding: RecyclerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        lateinit var currentItem: Apple

        init {
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra("item", currentItem)
                binding.root.context.startActivity(intent)
            }

            binding.root.setOnLongClickListener {
                val builder = AlertDialog.Builder(binding.root.context)
                builder.setTitle("상품 삭제")
                builder.setMessage("상품을 정말로 삭제하시겠습니까?")
                builder.setIcon(R.drawable.ic_bubble_chat)

                val position = adapterPosition

                val listener = object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        when (p1) {
                            DialogInterface.BUTTON_POSITIVE ->
                                if (position != RecyclerView.NO_POSITION) {
                                    val removedItem = list.removeAt(position)
                                    this@RecyclerViewAdapter.notifyItemRemoved(position)
                                }

                            DialogInterface.BUTTON_NEGATIVE ->
                                return
                        }
                    }
                }

                builder.setPositiveButton("확인", listener)
                builder.setNegativeButton("취소", listener)
                builder.show()

                true
            }
        }

        fun bind(item: Apple) {
            currentItem = item
            binding.ivPicture.setImageResource(item.picture)
            binding.tvTitle.text = item.title
            binding.tvAddress.text = item.address
            binding.tvPrice.text = MainActivity().converter(item.price) + "원"
            binding.tvReply.text = item.reply
            binding.tvLike.text = item.like

        }
    }
}