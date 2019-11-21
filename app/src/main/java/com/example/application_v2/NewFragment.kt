package com.example.application_v2

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.application_v2.databinding.FragmentLoginBinding
import com.example.application_v2.databinding.FragmentNewBinding


class NewFragment : Fragment() {

    private lateinit var binding: FragmentNewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_new, container, false
        )

        binding.button.setOnClickListener {
            Toast.makeText(context,"Hello Nine.", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}
