package com.example.vistore

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.vistore.actvities.AdministratorActivity
import com.example.vistore.databinding.ActivityMainBinding
import com.example.vistore.fragments.*
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.objects.UserObject
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.replaceActivity
import com.example.vistore.utilits.replaceFragmentWithNoBackStack
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)       // binding initializing
        setContentView(binding.root)

        //setting context to the APP_ACTIVITY
        APP_ACTIVITY = this

        UserObject.checkUser()    // if currentUser !null --> retrieve remote data (admin or user)

        // replace activity if !null current user is admin
        lifecycleScope.launch {                      // lifecycleScope - destroys after activity is destroyed
            if(FirebaseObject.isCurrentUserAdmin()){
                APP_ACTIVITY.replaceActivity(AdministratorActivity())
            } else {
                replaceFragmentWithNoBackStack(HomeFragment())      // if mainActivity runs replace fragment
            }
        }

        setStatusBarParams()
    }

    override fun onStart() {
        super.onStart()

        /**changing fragments by clicking on NavBar element*/
        binding.bottomNavBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> replaceFragmentWithNoBackStack(HomeFragment())
                R.id.ic_menu -> replaceFragmentWithNoBackStack(MenuFragment())

                // if user null --> EmptyBasketFragment
                R.id.ic_basket -> lifecycleScope.launch {                      // lifecycleScope - destroys after activity is destroyed
                    if (Firebase.auth.currentUser == null) replaceFragmentWithNoBackStack(
                        EmptyBasketFragment()
                    )
                    //if user not null but basket is empty --> EmptyBasketFragment
                    else if (FirebaseObject.checkIsBasketEmpty()) replaceFragmentWithNoBackStack(
                        EmptyBasketFragment()
                    )
                    // if user not null and basket is not empty --> BasketFragment
                    else replaceFragmentWithNoBackStack(BasketFragment())
                }


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