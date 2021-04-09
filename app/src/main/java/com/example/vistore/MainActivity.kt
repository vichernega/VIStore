package com.example.vistore

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vistore.databinding.ActivityMainBinding
import com.example.vistore.fragments.*
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.objects.User
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.replaceFragment
import com.example.vistore.utilits.replaceFragmentWithNoBackStack
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
        replaceFragmentWithNoBackStack(HomeFragment())
    }

    override fun onStart() {
        super.onStart()

        /**changing fragments by clicking on NavBar element*/
        binding.bottomNavBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> replaceFragmentWithNoBackStack(HomeFragment())
                R.id.ic_menu -> replaceFragmentWithNoBackStack(MenuFragment())
                R.id.ic_basket -> replaceFragmentWithNoBackStack(BasketFragment())
                R.id.ic_my_account -> if (Firebase.auth.currentUser != null) replaceFragmentWithNoBackStack(ProfileFragment())
                                        else replaceFragmentWithNoBackStack(EmptyUserProfileFragment())

            }
            true
        }
    }


    // back navigation
    override fun onBackPressed() {

        val currentFragment = supportFragmentManager.findFragmentById(R.id.activity_container)

        //from HomeFragment app closes
        if (currentFragment != null) {
            if(binding.bottomNavBar.selectedItemId == R.id.ic_home && currentFragment is HomeFragment){
                super.onBackPressed()
                finish()
            }
            //from other main fragments user navigates to HomeFragment
            else if (currentFragment is MenuFragment || currentFragment is BasketFragment ||
                currentFragment is ProfileFragment || currentFragment is EmptyUserProfileFragment) {

                binding.bottomNavBar.selectedItemId = R.id.ic_home
            }
            //from other fragments (GoodFragment / ChangeUserInfoFragment) user navigates by back stack
            else {
                super.onBackPressed()
            }
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

    fun hideBottomNavBar(){
        binding.bottomNavBar.visibility = View.GONE
    }
    fun showBottomNavBar(){
        binding.bottomNavBar.visibility = View.VISIBLE
    }

}