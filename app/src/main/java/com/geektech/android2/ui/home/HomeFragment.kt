package com.geektech.android2.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.geektech.android2.R
import com.geektech.android2.databinding.FragmentHomeBinding
import com.geektech.android2.models.News

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: NewsAdapter
    private var bolean: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NewsAdapter {
            val news = adapter.getItem(it)
            val bundle = Bundle()
            bundle.putSerializable("news", news)
            bolean=true
            findNavController().navigate(R.id.newsFragment, bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.newsFragment)
        }
        parentFragmentManager.setFragmentResultListener(
            "rk_news",
            viewLifecycleOwner
        ) { requestKey, bundle ->
            val news = bundle.getSerializable("news") as News
            val poss: Int? = null
            if (bolean) {
                poss?.let { adapter.replaceItem(news, it) }
            } else {
                adapter.addItem(news)
            }
            Log.e("Home", "text${news.tittle}${news.createdAt}")



            binding.recyclerView.adapter = adapter
        }

       // override fun onDestroyView() {
           // super.onDestroyView()
            //binding = null
        }
    }
