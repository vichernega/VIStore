package com.example.vistore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.vistore.R
import com.example.vistore.adapters.UserOrdersRecyclerViewAdapter
import com.example.vistore.databinding.FragmentUserOrdersBinding
import com.example.vistore.objects.Order
import com.example.vistore.viewmodels.UserOrdersViewModel

class UserOrdersFragment(private val userId: String) : Fragment(R.layout.fragment_user_orders) {

    private lateinit var binding: FragmentUserOrdersBinding                     //ViewBinding
    private val viewModel by viewModels<UserOrdersViewModel>()
    private lateinit var recyclerViewAdapter: UserOrdersRecyclerViewAdapter     // RecyclerView

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUserOrdersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getOrderList(userId)
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.orderListLiveData.observe(viewLifecycleOwner, Observer {
            if(it.isEmpty()){
                binding.tvEmpty.visibility = View.VISIBLE
            } else {
                initRecyclerViewAdapter(it)
            }

            binding.progressBar.visibility = View.GONE
        })
    }

    fun initRecyclerViewAdapter(orderList: List<Order>){
        recyclerViewAdapter = UserOrdersRecyclerViewAdapter(orderList)
        binding.userOrdersRecyclerView.adapter = recyclerViewAdapter
    }

}