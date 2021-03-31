package com.example.vistore

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.vistore.databinding.ActivityMainBinding
import com.example.vistore.fragments.*
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.objects.User
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setting context to the APP_ACTIVITY
        APP_ACTIVITY = this

        User.checkUser()    // if user !null --> retrieve remote data

        setStatusBarParams()
    }

    override fun onStart() {
        super.onStart()

        /**changing fragments by clicking on NavBar element*/
        binding.bottomNavBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> replaceFragment(HomeFragment())
                R.id.ic_menu -> replaceFragment(MenuFragment())
                R.id.ic_basket -> replaceFragment(BasketFragment())
                R.id.ic_my_account -> if (Firebase.auth.currentUser != null) replaceFragment(ProfileFragment())
                                        else replaceFragment(EmptyUserProfileFragment())

            }
            true
        }
    }


    //status bar appearance
    fun setStatusBarParams(){
        //black icons color
        val view: View = window.decorView
        view.setSystemUiVisibility(view.getSystemUiVisibility() or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        // white background color
        window.statusBarColor = this.resources.getColor(R.color.white)
    }

}