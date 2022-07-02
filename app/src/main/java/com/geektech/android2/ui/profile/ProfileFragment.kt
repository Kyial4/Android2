package com.geektech.android2.ui.profile

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.geektech.android2.Prefs
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
        val listener = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                Prefs(requireContext()).savestateEt(binding.editText12.text.toString())
            }

        }
        binding.editText12.addTextChangedListener(listener)

    }


}
