package com.example.vistore.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vistore.MainActivity
import com.example.vistore.R
import com.example.vistore.actvities.RegisterActivity
import com.example.vistore.databinding.FragmentEmptyUserProfileBinding
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.replaceActivity
import com.example.vistore.utilits.replaceFragment
import com.example.vistore.utilits.showToast


class EmptyUserProfileFragment : Fragment(R.layout.fragment_empty_user_profile) {

    private lateinit var binding: FragmentEmptyUserProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // binding connection
        binding = FragmentEmptyUserProfileBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onResume() {
        super.onResume()

        // clicking on LOG IN button
        binding.btnLogin.setOnClickListener { startLogIn() }

        // clicking on SIGN UP button
        binding.btnSignUp.setOnClickListener { startSignUp() }


    }

    // changing activity and fragment to start login or sig up
    fun startLogIn(){
        showToast("IN LOGIN. FIRST LINE")
        APP_ACTIVITY.replaceActivity(RegisterActivity())     //MainActivity().replaceActivity(RegisterActivity())
        RegisterActivity().replaceFragment(LoginFragment())
    }

    fun startSignUp(){
        showToast("IN SIGN UP. FIRST LINE")
        APP_ACTIVITY.replaceActivity(RegisterActivity())
        RegisterActivity().replaceFragment(SignUpFragment())
    }

}