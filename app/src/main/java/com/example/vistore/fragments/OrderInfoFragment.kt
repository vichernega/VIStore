package com.example.vistore.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vistore.R
import com.example.vistore.adapters.OrderGoodRecyclerViewAdapter
import com.example.vistore.databinding.FragmentOrderInfoBinding
import com.example.vistore.objects.Order
import com.example.vistore.objects.OrderObject

class OrderInfoFragment() : Fragment(R.layout.fragment_order_info) {

    private lateinit var binding: FragmentOrderInfoBinding
    private lateinit var recyclerViewAdapter: OrderGoodRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOrderInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOrderInfo()
    }

    fun setOrderInfo(){
        binding.tvOrderIdVal.text = OrderObject.orderId
        binding.tvFullNameVal.text = "${OrderObject.userName} ${OrderObject.userSurname}"
        binding.tvCountryVal.text = OrderObject.userCountry
        binding.tvTownVal.text = OrderObject.userTown
        binding.tvAddressVal.text = OrderObject.userAddress

        // set delivery and payment type
        binding.tvDeliveryVal.text = OrderObject.checkDelivery()
        binding.tvPaymentVal.text = OrderObject.checkPayment()

        /** BUG */
        // set confirm status appearance
        if (OrderObject.checkIsConfirmed()){
            binding.tvConfirmed.text = "Confirmed"
            binding.confirmStatus.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
        } else {
            binding.tvConfirmed.text = "Not confirmed"
            binding.confirmStatus.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorDarkBrown))
        }

        // set receive status appearance
        if (OrderObject.checkIsReceived()){
            binding.tvReceived.text = "Received"
            binding.receiveStatus.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
        } else {
            binding.tvReceived.text = "Not received"
            binding.receiveStatus.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorDarkBrown))
        }

        // good list in recycler view
        initRecyclerView()

        binding.tvOrderTotalValue.text = OrderObject.totalValue
    }

    fun initRecyclerView(){
        recyclerViewAdapter = OrderGoodRecyclerViewAdapter(OrderObject.goodsList)
        binding.goodListRecyclerView.adapter = recyclerViewAdapter
    }

}