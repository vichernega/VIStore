package com.example.vistore.actvities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.vistore.R
import com.example.vistore.databinding.ActivityAdministratorBinding
import com.example.vistore.utilits.ADMINISTRATOR_ACTIVITY

class AdministratorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdministratorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdministratorBinding.inflate(layoutInflater)       // binding initializing
        setContentView(binding.root)

        // setting context
        ADMINISTRATOR_ACTIVITY = this

        setStatusBarParams()
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