package com.example.vistore.actvities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.vistore.R
import com.example.vistore.fragments.RegisterOptionsFragment
import com.example.vistore.utilits.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setStatusBarParams()

        //setting context to the APP_ACTIVITY
        REGISTER_ACTIVITY = this

        replaceFragment(RegisterOptionsFragment())
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.activity_container)

        if (currentFragment is RegisterOptionsFragment){
            REGISTER_ACTIVITY.replaceActivity(APP_ACTIVITY)
        } else {
            super.onBackPressed()
        }
    }

    // status bar appearance
    fun setStatusBarParams(){
        //white icons color
        val view: View = window.decorView
        view.systemUiVisibility = view.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        // dark background color
        window.statusBarColor = this.resources.getColor(R.color.colorDarkBrown)
    }
}