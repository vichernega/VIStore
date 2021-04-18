package com.example.vistore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vistore.R
import com.example.vistore.databinding.FragmentAdminMainBinding
import com.example.vistore.objects.Administrator
import com.example.vistore.objects.UserObject
import com.example.vistore.utilits.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AdminMainFragment : Fragment(R.layout.fragment_admin_main) {

    private lateinit var binding: FragmentAdminMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAdminMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickListeners()
        setAdminData()
    }

    fun setUpClickListeners(){

        // on Users click
        binding.usersContainer.setOnClickListener { replaceFragment(UsersFragment()) }

        // on Orders click
        binding.ordersContainer.setOnClickListener { replaceFragment(OrdersToConfirmFragment()) }

        //on Log Out click
        binding.logoutContainer.setOnClickListener {
            Firebase.auth.signOut()                         // log out
            UserObject.clear()                                    // clear user info
            Administrator.clear()                           // clear admin info
            ADMINISTRATOR_ACTIVITY.replaceActivity(APP_ACTIVITY)     // return to MainActivity
        }
    }

    private fun setAdminData(){
        binding.tvAdminEmail.setText(Administrator.email)
    }
}