package com.example.lico

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.lico.databinding.FragmentRegisterBinding


/**
 * A simple [Fragment] subclass.
 * Use the [Register.newInstance] factory method to
 * create an instance of this fragment.
 */
class Register : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentRegisterBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        binding.registerBtn.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_register2_to_homepage)
        }

        binding.login.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_register2_to_login)
        }

        return binding.root
    }
}