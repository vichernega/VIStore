package com.example.vistore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vistore.R
import com.example.vistore.actvities.RegisterActivity
import com.example.vistore.databinding.FragmentRegisterOptionsBinding
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.replaceActivity
import com.example.vistore.utilits.replaceFragment

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