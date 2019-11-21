package com.example.application_v2.login

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.application_v2.R
import com.example.application_v2.database.MyDatabase
import com.example.application_v2.databinding.FragmentLoginBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = MyDatabase.getInstance(application).userDao
        val viewmodelfactory = LoginViewModelFactory(dataSource, application)
        val viewModel = ViewModelProviders.of(this,viewmodelfactory).get(LoginViewModel::class.java)




        binding.buttonRegister.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        viewModel.gotoHome.observe(this, Observer {
            if (it) {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                )
            }
        })

        viewModel.showToast.observe(this, Observer {
            if (it) {
                Toast.makeText(context,"Please input correct informations.", Toast.LENGTH_SHORT).show()
                viewModel._showToast.value = false
            }
        })

        viewModel.showToastHaveUser.observe(this, Observer {
            if (it) {
                Toast.makeText(context,"There are no users in the system.", Toast.LENGTH_SHORT).show()
                viewModel._showToastHaveUser.value = false
            }
        })

        viewModel.showToastCheckPassword.observe(this, Observer {
            if (it) {
                Toast.makeText(context,"Please check your password.", Toast.LENGTH_SHORT).show()
                viewModel._showToastCheckPassword.value = false
            }
        })


        setHasOptionsMenu(true)


        binding.loginViewModel = viewModel

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}