package com.example.application_v2.addProduct


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.application_v2.R
import com.example.application_v2.database.MyDatabase
import com.example.application_v2.databinding.FragmentAddBinding
import com.google.android.material.snackbar.Snackbar



class AddGymFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAddBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_add, container, false)

        binding.buttonCancel.setOnClickListener {
            it.findNavController().navigate(R.id.action_addFragment_to_homeFragment)
        }

        val application = requireNotNull(this.activity).application
        val dataSource = MyDatabase.getInstance(application).gymDao
        val viewmodelfactory = AddProductViewModelFactory(dataSource, application)
        val viewModel = ViewModelProviders.of(this, viewmodelfactory)
            .get(AddProductViewModel::class.java)

        viewModel.gotoHome.observe(this, Observer {
            if (it) {
                findNavController().navigate(
                    AddGymFragmentDirections.actionAddFragmentToHomeFragment()
                )
            }
        })

        viewModel.showSnackBarEvent.observe(this, Observer {
            Snackbar.make(
                activity!!.findViewById(android.R.id.content),
                "Please input correct informations.",
                Snackbar.LENGTH_SHORT // How long to display the message.
            ).show()
        })


        // Inflate the layout for this fragment

        binding.addViewModel = viewModel

        return binding.root
    }


}
