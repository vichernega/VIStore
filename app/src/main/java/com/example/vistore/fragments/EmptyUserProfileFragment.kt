package com.example.vistore.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vistore.R
import com.example.vistore.actvities.RegisterActivity
import com.example.vistore.databinding.FragmentEmptyUserProfileBinding
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.replaceActivity
import com.example.vistore.utilits.replaceFragment


class EmptyUserProfileFragment : Fragment(R.layout.fragment_empty_user_profile) {

    private lateinit var binding: FragmentEmptyUserProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // binding connection
        binding = FragmentEmptyUserProfileBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onResume() {
        super.onResume()

        // clicking on REGISTER button --> changing activity
        binding.btnRegister.setOnClickListener { APP_ACTIVITY.replaceActivity(RegisterActivity()) }

    }


}