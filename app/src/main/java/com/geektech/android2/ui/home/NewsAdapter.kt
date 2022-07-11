package com.geektech.android2.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.geektech.android2.databinding.FragmentNewsBinding
import com.geektech.android2.databinding.ItemNewsBinding
import com.geektech.android2.models.News
import com.geektech.android2.ui.home.NewsAdapter.ViewHolder
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(private val onClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {
    private var list = arrayListOf<News>()
    var onItemLongClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onClick(position)
        }

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.DKGRAY)
        } else {
            holder.itemView.setBackgroundColor(Color.GRAY)
        }
    }

    override fun getItemCount(): Int = list.size
    fun addItem(news: News) {
        list.add(0, news)
        notifyItemInserted(list.indexOf(news))

    }

    fun getItem(pos: Int): News {
        return list[pos]

    }

    fun replaceItem(news: News, poss: Int) {
        list.set(poss, news)
        notifyItemChanged(poss)
    }

    fun addItems(list: List<News?>?) {
        this.list.addAll(listOf())
        notifyDataSetChanged()

    }

    fun addList(list: List<News>) {
        this.list = list as ArrayList<News>
        Collections.reverse(this.list)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateItem(news: News?, position: Int) {
        list[position] = news!!
        notifyItemChanged(position)
    }


    inner class ViewHolder(private var binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.textTittle.text = news.tittle
            binding.textDate.text = getDate(news.createdAt, "dd MMM YYYY")
            binding.textTime.text = getDate(news.createdAt, "HH:MM,")
        }

        fun getDate(milliSeconds: Long, dateFormat: String): String {
            val formater = SimpleDateFormat(dateFormat)
            val calendar = Calendar.getInstance();
            calendar.timeInMillis = milliSeconds;
            return formater.format(calendar.time);
        }


        //1. Закончить авторизацию по номеру телефона
        // 2. Обратный отсчет
        // Bonus. По завершении отсчета показать окно для ввода номера

    }
}
