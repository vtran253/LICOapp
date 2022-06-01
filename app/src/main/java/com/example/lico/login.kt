package com.example.lico

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.lico.databinding.FragmentHomepageBinding
import com.example.lico.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 * Use the [login.newInstance] factory method to
 * create an instance of this fragment.
 */
class login : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.login.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_login_to_homepage)
        }

        binding.register.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_login_to_register2)
        }

        return binding.root
    }

}