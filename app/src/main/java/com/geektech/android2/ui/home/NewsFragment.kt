package com.geektech.android2.ui.home

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.geektech.android2.App
import com.geektech.android2.databinding.FragmentNewsBinding
import com.geektech.android2.models.News

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private var news: News? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        news = arguments?.getSerializable("news") as News?

        news?.let {
            binding.editText.setText(it.tittle)
        }
        binding.btnSave.setOnClickListener {
            save()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun save() {
        val text = binding.editText.text.toString().trim()
        var news=News(0,text, System.currentTimeMillis())
        App.dataBase.newsDao().insert(news)
        if (news == null) {
           news = News(0,text, System.currentTimeMillis())
        } else {
           news?.tittle = text
        }

            val bundle = Bundle()
            bundle.putSerializable("news", news)

            parentFragmentManager.setFragmentResult("rk_news", bundle)
            findNavController().navigateUp()


    }
}

