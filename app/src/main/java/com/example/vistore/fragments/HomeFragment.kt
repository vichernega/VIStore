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
import com.example.vistore.databinding.FragmentHomeBinding
import com.example.vistore.viewmodels.HomeViewModel
import com.example.vistore.objects.Good


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var recyclerViewAdapter: CommonRecyclerViewAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getProducts()

    }

    private fun observeViewModel() {

        viewModel.responseListLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.progressBar.visibility = View.GONE      // hide progress bar after list loaded
                initRecyclerView(it)
            }
        })
    }

    fun initRecyclerView(productsList: List<Good>){
        recyclerViewAdapter = CommonRecyclerViewAdapter(productsList)
        gridLayoutManager = GridLayoutManager(activity, 2, LinearLayoutManager.VERTICAL, false)
        binding.homeRecyclerView.layoutManager = gridLayoutManager
        binding.homeRecyclerView.adapter = recyclerViewAdapter

    }

}