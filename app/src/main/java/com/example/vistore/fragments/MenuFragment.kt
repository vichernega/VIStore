package com.example.vistore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.vistore.R
import com.example.vistore.adapters.MenuRecyclerViewAdapter
import com.example.vistore.databinding.FragmentMenuBinding
import com.example.vistore.viewmodels.MenuViewModel


class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding
    private val viewModel by viewModels<MenuViewModel>()
    private lateinit var recyclerViewAdapter: MenuRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViewAdapter()
    }

    fun initRecyclerViewAdapter(){

        // parameter - list of categories from repository
        recyclerViewAdapter = MenuRecyclerViewAdapter(viewModel.categoryList)
        binding.menuRecyclerView.adapter = recyclerViewAdapter
    }
}