package com.geektech.android2.ui.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.geektech.android2.R
import com.geektech.android2.databinding.FragmentNewsBinding
import com.geektech.android2.databinding.ItemNewsBinding
import com.geektech.android2.databinding.PagerBoardBinding
import com.geektech.android2.models.News
import com.geektech.android2.ui.home.NewsAdapter
import com.geektech.android2.ui.home.NewsAdapter.ViewHolder

class BoardAdapter(private val onClickStart: () -> Unit) :
    RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    private val tittles = arrayListOf("Салам", "Привет", "Hello")
    private val images = arrayListOf (R.drawable.ic_1, R.drawable.ic_2, R.drawable.ic_3)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PagerBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)

    }

    override fun getItemCount(): Int = tittles.size


    inner class ViewHolder(private var binding: PagerBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.imageView.setImageResource(images[position])
            binding.textTittle.text = tittles[position]
            if (position == tittles.size - 1) {
                binding.btnStart.visibility = View.VISIBLE
            } else {
                binding.btnStart.visibility = View.INVISIBLE
            }

            binding.btnStart.setOnClickListener {
                onClickStart()
            }
        }
    }
}

