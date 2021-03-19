package com.example.vistore.utilits

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vistore.MainActivity
import com.example.vistore.R
import java.security.AccessController.getContext

// file where different functions are inflated*/


fun Fragment.replaceFragment(fragment: Fragment){
    fragmentManager?.beginTransaction()
        ?.replace(R.id.activity_container, fragment)
        ?.commit()

}
fun AppCompatActivity.replaceFragment(fragment: Fragment){
    supportFragmentManager.beginTransaction()
        .replace(R.id.activity_container, fragment)
        .commit()
}

fun showToast(str: String){
    Toast.makeText(APP_ACTIVITY, str, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.replaceActivity(activity: AppCompatActivity){
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

