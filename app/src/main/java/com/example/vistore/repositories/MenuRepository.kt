package com.example.vistore.repositories

import com.example.vistore.objects.Category

class MenuRepository {

    private val _categoryList = listOf<Category>(Category("cream"), Category("lipstick"),
                                        Category("liquid"), Category("palette"),
                                        Category("pencil"), Category("powder"))

    val categoryList get() = _categoryList

}