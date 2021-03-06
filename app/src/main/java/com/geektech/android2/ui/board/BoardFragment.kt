package com.geektech.android2.ui.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.geektech.android2.Prefs
import com.geektech.android2.R
import com.geektech.android2.databinding.FragmentBoardBinding
import com.geektech.android2.models.Board

class BoardFragment : Fragment() {
    private lateinit var binding: FragmentBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BoardAdapter {
            findNavController().navigateUp()
        }
        /* binding.viewPager.adapter=adapter
         val dots  =binding.wormDotsIndicator
         val viewpager2=binding.viewPager
         dots.setViewPager2(viewpager2)*/
        adapter.addItem(Board(R.raw.lottie1, "Hello", "Description"))
        adapter.addItem(Board(R.raw.lottie2, "Привет", "Description"))
        adapter.addItem(Board(R.raw.lottie3, "Салам", "Description"))
        binding.viewPager.adapter = adapter
        binding.wormDotsIndicator.setViewPager2(binding.viewPager)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (position == 2) {
                    binding.skip.visibility = View.INVISIBLE
                } else {
                    binding.skip.visibility = View.VISIBLE
                }
            }
        })
        binding.skip.setOnClickListener {
            close()
        }
    }

    private fun close() {
        Prefs(requireContext()).saveState()
        findNavController().navigateUp()

    }
    //1. Закончить авторизацию по номеру телефона
//2. Обратный отсчет
//Bonus. По завершении отсчета показать окно для ввода номера

}









