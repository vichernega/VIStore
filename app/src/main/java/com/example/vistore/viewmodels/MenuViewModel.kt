package com.example.vistore.viewmodels

import androidx.lifecycle.ViewModel
import com.example.vistore.repositories.MenuRepository

class MenuViewModel : ViewModel() {

    private val repo: MenuRepository = MenuRepository()
    val categoryList = repo.categoryList
}