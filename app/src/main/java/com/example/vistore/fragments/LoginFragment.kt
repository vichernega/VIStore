package com.example.vistore.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.vistore.R
import com.example.vistore.databinding.FragmentLoginBinding
import com.example.vistore.models.LoginViewModel
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.REGISTER_ACTIVITY
import com.example.vistore.utilits.replaceActivity
import com.example.vistore.utilits.showToast


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        observeViwModel()
    }


    //observing mutableLiveData in RegisterRepository
    private fun observeViwModel() {
        viewModel.userMutableLiveData.observe(viewLifecycleOwner, Observer {
            showToast("Welcome!")
            REGISTER_ACTIVITY.replaceActivity(APP_ACTIVITY)
        })
    }


    private fun setOnClickListeners() {

        // ON LOG IN CLICK
        binding.btnLogIn.setOnClickListener {

            if (isEditTextEmpty()) {

                binding.tvLogInErrorField.text = getString(R.string.error_fill_in_all_of_the_fields)

            } else {
                viewModel.login(binding.etLogInEmail.text.toString().trim { it <= ' ' },
                                binding.etLogInPassword.text.toString().trim { it <= ' ' })
            }
        }
    }


    fun isEditTextEmpty(): Boolean{
        return binding.etLogInEmail.text.trim { it <= ' ' }.isEmpty() ||
                binding.etLogInPassword.text.trim { it <= ' ' }.isEmpty()
    }

}