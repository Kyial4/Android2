package com.geektech.android2.ui.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.android2.R
import com.geektech.android2.databinding.PagerBoardBinding
import com.geektech.android2.models.Board

class BoardAdapter(private val onClickStart: () -> Unit) :
    RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    private val list = arrayListOf<Board>()
    //private val images = arrayListOf (R.drawable.ic_1, R.drawable.ic_2, R.drawable.ic_3)
    //private val anim= arrayListOf(R.raw.lottie1,R.raw.lottie2,R.raw.lottie3)




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
        holder.bind(list[position])

    }

    override fun getItemCount(): Int = list.size
    fun addItem(board: Board) {
        list.add(board)

    }


    inner class ViewHolder(private var binding: PagerBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(board: Board) {
            binding.lottieView.setAnimation(board.lottie)
            binding.textTittle.text = board.tittle
            if (list.lastIndexOf(board)==list.lastIndex) {
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




