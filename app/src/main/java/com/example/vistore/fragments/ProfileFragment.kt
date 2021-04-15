package com.example.vistore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vistore.R
import com.example.vistore.databinding.FragmentProfileBinding
import com.example.vistore.objects.UserObject
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.replaceFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()
        APP_ACTIVITY.showBottomNavBar()
    }

    override fun onStart() {
        super.onStart()

        showUserData()
    }


    private fun setOnClickListeners() {

        //on personal information click
        binding.personalInformationContainer.setOnClickListener { replaceFragment(ChangeUserInfoFragment()) }

        //on orders click
        binding.ordersContainer.setOnClickListener { /*replaceFragment(ShowOrders())*/ }

        //on Log Out click
        binding.logoutContainer.setOnClickListener {
            Firebase.auth.signOut()
            UserObject.clear()
            replaceFragment(EmptyUserProfileFragment())
        }
    }

    // show user data in fragment Text Views (name + surname, email)
    fun showUserData(){
        binding.tvUserFullname.text = UserObject.name + " " + UserObject.surname
        binding.tvUserEmail.text = UserObject.email
    }
}