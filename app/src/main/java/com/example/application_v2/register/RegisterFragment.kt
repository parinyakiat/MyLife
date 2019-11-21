package com.example.application_v2.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.application_v2.R
import com.example.application_v2.database.MyDatabase
import com.example.application_v2.databinding.FragmentRegisterBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = MyDatabase.getInstance(application).userDao
        val viewmodelfactory = RegisterViewModelFactory(dataSource, application)
        val viewModel = ViewModelProviders.of(this, viewmodelfactory)
            .get(RegisterViewModel::class.java)

        binding.buttonCancel.setOnClickListener {
            it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        viewModel.gotoLogin.observe(this, Observer {
            if (it) {
                findNavController().navigate(
                    RegisterFragmentDirections
                        .actionRegisterFragmentToLoginFragment()
                )
            }
        })

        viewModel.showToast.observe(this, Observer {
            if (it) {
                Toast.makeText(context, "Please input correct informations.", Toast.LENGTH_SHORT)
                    .show()
                viewModel._showToast.value = false
            }
        })

        viewModel.showToastPassword.observe(this, Observer {
            if (it) {
                Toast.makeText(context, "Please check your password.", Toast.LENGTH_LONG).show()
                viewModel._showToastPassword.value = false
            }
        })

        viewModel.showToastHaveUser.observe(this, Observer {
            if (it) {
                Toast.makeText(context, "There is a user in the system.", Toast.LENGTH_LONG).show()
                viewModel._showToastHaveUser.value = false
            }
        })

        binding.registerViewModel = viewModel

        return binding.root
    }


}
