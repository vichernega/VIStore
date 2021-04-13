package com.example.vistore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vistore.R
import com.example.vistore.adapters.CommonRecyclerViewAdapter
import com.example.vistore.databinding.FragmentMenuResultBinding
import com.example.vistore.objects.Good
import com.example.vistore.viewmodels.MenuResultViewModel

class MenuResultFragment(private val category: String) : Fragment(R.layout.fragment_menu_result) {

    private lateinit var binding: FragmentMenuResultBinding
    private val viewModel by viewModels<MenuResultViewModel>()
    private lateinit var recyclerViewAdapter: CommonRecyclerViewAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMenuResultBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProductsByCategory(category)
        observeViewModel()
    }

    private fun observeViewModel(){

        viewModel.categoryResultList.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE      // hide progress bar after list loaded
            initRecyclerView(it)    // load list in recyclerViewAdapter
        })
    }

    fun initRecyclerView(productsList: List<Good>){
        recyclerViewAdapter = CommonRecyclerViewAdapter(productsList)
        gridLayoutManager = GridLayoutManager(activity, 2, LinearLayoutManager.VERTICAL, false)
        binding.menuResultRecyclerView.layoutManager = gridLayoutManager
        binding.menuResultRecyclerView.adapter = recyclerViewAdapter

    }
}