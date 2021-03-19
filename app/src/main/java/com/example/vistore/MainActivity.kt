package com.example.vistore

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.vistore.databinding.ActivityMainBinding
import com.example.vistore.fragments.BasketFragment
import com.example.vistore.fragments.EmptyUserProfileFragment
import com.example.vistore.fragments.HomeFragment
import com.example.vistore.fragments.MenuFragment
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.replaceFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setting context to the APP_ACTIVITY
        APP_ACTIVITY = this

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
                R.id.ic_my_account -> replaceFragment(EmptyUserProfileFragment()) // add user check

            }
            true
        }
    }

    fun setStatusBarParams(){
        //black icons color
        val view: View = window.decorView
        view.setSystemUiVisibility(view.getSystemUiVisibility() or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        // white background color
        window.statusBarColor = this.resources.getColor(R.color.white)

        // White ICONS
        /*val view: View = window.decorView
           view.setSystemUiVisibility(view.getSystemUiVisibility() and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv())*/
    }

}