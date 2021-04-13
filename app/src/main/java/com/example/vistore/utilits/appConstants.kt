package com.example.vistore.utilits

import com.example.vistore.MainActivity
import com.example.vistore.actvities.RegisterActivity

lateinit var APP_ACTIVITY: MainActivity
lateinit var REGISTER_ACTIVITY: RegisterActivity

const val BASE_URL = "https://makeup.p.rapidapi.com"
// values for api key and host
val HEADERS = hashMapOf<String, String>("x-rapidapi-key" to "3e7abad104mshab610049e8e18d7p16ff5fjsn5e4dbb01f073",
                                        "x-rapidapi-host" to "makeup.p.rapidapi.com")

