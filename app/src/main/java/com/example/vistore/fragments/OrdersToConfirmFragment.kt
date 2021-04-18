package com.example.vistore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.vistore.R
import com.example.vistore.adapters.OrdersToConfirmRecyclerViewAdapter
import com.example.vistore.databinding.FragmentOrdersToConfirmBinding
import com.example.vistore.objects.Order
import com.example.vistore.viewmodels.OrdersToConfirmViewModel

class OrdersToConfirmFragment : Fragment(R.layout.fragment_orders_to_confirm) {

    private lateinit var binding: FragmentOrdersToConfirmBinding            // View Binding
    private val viewModel by viewModels<OrdersToConfirmViewModel>()
    private lateinit var recyclerViewAdapter: OrdersToConfirmRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOrdersToConfirmBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getOrderList()
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.orderToConfirmListLiveData.observe(viewLifecycleOwner, Observer {
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
        recyclerViewAdapter = OrdersToConfirmRecyclerViewAdapter(orderList)
        binding.userOrdersRecyclerView.adapter = recyclerViewAdapter

    }

}