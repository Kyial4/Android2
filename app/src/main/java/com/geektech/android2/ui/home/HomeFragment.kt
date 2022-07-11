package com.geektech.android2.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.geektech.android2.App
import com.geektech.android2.R
import com.geektech.android2.databinding.FragmentHomeBinding
import com.geektech.android2.models.News
import android.text.TextWatcher as TextWatcher1

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: NewsAdapter
    private var bolean: Boolean = false
    private var list = arrayListOf<News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NewsAdapter {
            val news = adapter.getItem(it)
            val bundle = Bundle()
            bundle.putSerializable("news", news)
            bolean = true
            findNavController().navigate(R.id.newsFragment, bundle)
        }
        val list = App.dataBase.newsDao().getall()
        adapter.addItems(list)
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
        adapter.onItemLongClick = {
            AlertDialog.Builder(view?.context).setTitle("Warning!")
                .setMessage("Вы уверены, что хотите удалить?")
                .setNegativeButton("нет", null)
                .setPositiveButton("да") { dialog, which ->
                    val news = adapter.getItem(it)
                    adapter.removeItem(it)
                    App.dataBase.newsDao().delete(news)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(requireContext(), "удаление прошло успешно", Toast.LENGTH_SHORT)
                        .show()
                }.show()
        }


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
        binding.searchView.addTextChangedListener(object : TextWatcher1 {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                list = App.dataBase.newsDao().getSearch(s.toString()) as ArrayList<News>
                adapter.addList(list);
            }


        }
        )
        binding.recyclerView.adapter = adapter
        list = App.dataBase.newsDao().sortAll() as ArrayList<News>
        adapter.addList(list)





    }


    // override fun onDestroyView() {
        // super.onDestroyView()
        //binding = null

    }

