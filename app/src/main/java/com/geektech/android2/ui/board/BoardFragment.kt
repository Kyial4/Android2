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
import com.geektech.android2.databinding.FragmentBoardBinding

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
            close()
        }
        binding.viewPager.adapter = adapter
        val dotsIndicator = binding.dotsIndicator
        val viewPager = binding.viewPager
        viewPager.adapter = (adapter)
        dotsIndicator.setViewPager2(viewPager)
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

}









