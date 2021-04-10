package com.example.vistore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vistore.R
import com.example.vistore.databinding.FragmentRegisterOptionsBinding
import com.example.vistore.utilits.*

class RegisterOptionsFragment : Fragment(R.layout.fragment_register_options) {

    private lateinit var binding: FragmentRegisterOptionsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        // clicking on LOG IN button
        binding.btnLogin.setOnClickListener { replaceFragment(LoginFragment()) }

        // clicking on SIGN UP button
        binding.btnSignUp.setOnClickListener { replaceFragment(SignUpFragment()) }


    }

}