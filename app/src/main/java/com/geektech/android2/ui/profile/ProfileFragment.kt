package com.geektech.android2.ui.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.geektech.android2.R
import com.geektech.android2.databinding.ActivityMainBinding
import com.geektech.android2.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: ProfileFragmentBinding

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
    }

    private lateinit
    var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileFragmentBinding.inflate(layoutInflater)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clickImageButton.setOnClickListener {
            getContent.launch("image/*")
            textWatcher()
        }
    }

    /* private fun saveName(){
        val name= binding.editText.text
         pref.edit().putString("name",name.toString()).apply()
     }*/

    private fun textWatcher() {
        val pref = requireContext().getSharedPreferences("name", Context.MODE_PRIVATE)
        val name = binding.editText
        val listener = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                pref.edit().putString("name", p0.toString()).apply()
                Log.e("TAG", "textwather:${pref.getString("name", "defaultName")}")
                binding.tv.text=pref.getString("name","defaultName")
            }

        }
        binding.editText.addTextChangedListener(listener)


    }

}
