package com.example.vistore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.vistore.R
import com.example.vistore.adapters.CurrentUserOrdersRecyclerViewAdapter
import com.example.vistore.databinding.FragmentConfirmOrderBinding
import com.example.vistore.databinding.FragmentCurrentUserOrdersBinding
import com.example.vistore.objects.Order
import com.example.vistore.viewmodels.CurrentUserOrdersViewModel

class CurrentUserOrdersFragment : Fragment() {

    private lateinit var binding: FragmentCurrentUserOrdersBinding
    private val viewModel by viewModels<CurrentUserOrdersViewModel>()
    private lateinit var recyclerViewAdapter: CurrentUserOrdersRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCurrentUserOrdersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getOrderList()
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.orderList.observe(viewLifecycleOwner, Observer {
            if(it.isEmpty()){
                binding.tvEmpty.visibility = View.VISIBLE
            } else {
                initRecyclerViewAdapter(it)
                binding.tvEmpty.visibility = View.GONE
            }
            binding.progressBar.visibility = View.GONE
        })
    }

    fun initRecyclerViewAdapter(orderList: List<Order>){
        recyclerViewAdapter = CurrentUserOrdersRecyclerViewAdapter(orderList)
        binding.currentUserOrdersRecyclerView.adapter = recyclerViewAdapter
    }

}