package com.example.vistore.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vistore.R
import com.example.vistore.actvities.RegisterActivity
import com.example.vistore.databinding.FragmentEmptyUserProfileBinding
import com.example.vistore.databinding.FragmentProfileBinding
import com.example.vistore.utilits.replaceActivity
import com.example.vistore.utilits.replaceFragment


class ProfileFragment() : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)


    }



}