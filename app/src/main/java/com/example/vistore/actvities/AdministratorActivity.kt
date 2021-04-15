package com.example.vistore.actvities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.vistore.R
import com.example.vistore.databinding.ActivityAdministratorBinding
import com.example.vistore.fragments.AdminMainFragment
import com.example.vistore.fragments.RegisterOptionsFragment
import com.example.vistore.utilits.*

class AdministratorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdministratorBinding              // ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdministratorBinding.inflate(layoutInflater)       // binding initializing
        setContentView(binding.root)

        // setting context
        ADMINISTRATOR_ACTIVITY = this

        replaceFragment(AdminMainFragment())        // open adminMainFragment()
        setStatusBarParams()
    }


    // back navigation
    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.activity_container)
        if (currentFragment is AdminMainFragment){          // close app if AdminMainFragment is open
            super.onBackPressed()
            finish()
        } else {
            super.onBackPressed()                       // back stack for other fragments
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