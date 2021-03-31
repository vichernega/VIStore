package com.example.vistore.actvities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.vistore.R
import com.example.vistore.fragments.RegisterOptionsFragment
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.REGISTER_ACTIVITY
import com.example.vistore.utilits.replaceFragment

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setStatusBarParams()

        //setting context to the APP_ACTIVITY
        REGISTER_ACTIVITY = this

        replaceFragment(RegisterOptionsFragment())
    }


    // status bar appearance
    fun setStatusBarParams(){
        //white icons color
        val view: View = window.decorView
        view.setSystemUiVisibility(view.getSystemUiVisibility() and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv())
        // white background color
        window.statusBarColor = this.resources.getColor(R.color.colorDarkBrown)


    }
}