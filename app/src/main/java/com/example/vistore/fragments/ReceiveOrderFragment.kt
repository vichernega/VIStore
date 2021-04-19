package com.example.vistore.fragments

import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.vistore.R
import com.example.vistore.adapters.OrderGoodRecyclerViewAdapter
import com.example.vistore.databinding.FragmentReceiveOrderBinding
import com.example.vistore.objects.OrderObject
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.PERMISSION_CODE
import com.example.vistore.utilits.showToast
import com.example.vistore.viewmodels.ReceiveOrderViewModel
import java.util.jar.Manifest

class ReceiveOrderFragment : Fragment(R.layout.fragment_receive_order) {

    private lateinit var binding: FragmentReceiveOrderBinding
    private val viewModel by viewModels<ReceiveOrderViewModel>()
    private lateinit var recyclerViewAdapter: OrderGoodRecyclerViewAdapter

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentReceiveOrderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOrderInfo()
        setUpClickListeners()
    }

    fun setUpClickListeners(){

        // on RECEIVE click
        binding.btnReceive.setOnClickListener {
            viewModel.receive()
            binding.btnReceive.visibility = View.GONE
            setOrderInfo()                              // update tvIsReceived
            showToast("Received")
        }

        // on DOWNLOAD RECEIPT click
        binding.btnDownloadReceipt.setOnClickListener {

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){     // handle permission for devices with marshmallow and above
                // if permission is denied - request
                if (APP_ACTIVITY.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    APP_ACTIVITY.requestPermissions(permissions, PERMISSION_CODE)

                } else {            // permission already granted
                    viewModel.savePdf()
                }
            } else {                // system OS < marshmellow
                viewModel.savePdf()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){      // permission granted
                    viewModel.savePdf()
                } else { showToast("Permission denied") }       // permission denied, show error
            }
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
            binding.btnReceive.visibility = View.GONE                      // if order is not confirmed hide btnReceive
        }

        // set receive status appearance
        if (OrderObject.checkIsReceived()){
            binding.tvReceived.text = "Received"
            binding.receiveStatus.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
            binding.btnReceive.visibility = View.GONE                      // if order is received hide btnReceive
            binding.btnDownloadReceipt.visibility = View.VISIBLE           // if order is received user csn download receipt
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