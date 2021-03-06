package com.example.vistore.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.vistore.R
import com.example.vistore.adapters.OrderGoodRecyclerViewAdapter
import com.example.vistore.databinding.FragmentConfirmOrderBinding
import com.example.vistore.objects.OrderObject
import com.example.vistore.utilits.showToast
import com.example.vistore.viewmodels.ConfirmOrderViewModel


// this fragment must use OrderGoodRecyclerViewAdapter (for products) and settings from OrderInfoFragment
class ConfirmOrderFragment : Fragment(R.layout.fragment_confirm_order) {

    private lateinit var binding: FragmentConfirmOrderBinding
    private val viewModel by viewModels<ConfirmOrderViewModel>()
    private lateinit var recyclerViewAdapter: OrderGoodRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentConfirmOrderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOrderInfo()
        setUpClickListeners()
    }

    fun setUpClickListeners(){
        binding.btnConfirm.setOnClickListener {
            viewModel.confirm()
            binding.btnConfirm.visibility = View.GONE
            setOrderInfo()
            showToast("Confirmed")
        }
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
        initRecyclerViewAdapter()

        binding.tvOrderTotalValue.text = OrderObject.totalValue
    }

    fun initRecyclerViewAdapter(){
        recyclerViewAdapter = OrderGoodRecyclerViewAdapter(OrderObject.goodsList)
        binding.goodListRecyclerView.adapter = recyclerViewAdapter
    }

}