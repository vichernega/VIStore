package com.example.vistore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.vistore.R
import com.example.vistore.databinding.FragmentMakeOrderBinding
import com.example.vistore.objects.OrderObject
import com.example.vistore.objects.UserObject
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.replaceFragment
import com.example.vistore.viewmodels.MakeOrderViewModel


class MakeOrderFragment : Fragment(R.layout.fragment_make_order) {

    private lateinit var binding: FragmentMakeOrderBinding
    private val viewModel by viewModels<MakeOrderViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMakeOrderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.countTotalValue()
        observeViewModel()
        setUserInfoInEditTexts()
        setUpClickListeners()
    }


    private fun observeViewModel(){
        viewModel.orderTotalValueLiveData.observe(viewLifecycleOwner, Observer {
            binding.tvOrderTotalValue.text = it
        })
    }

    private fun setUserInfoInEditTexts(){
        binding.etName.setText(UserObject.name)
        binding.etSurname.setText(UserObject.surname)
        binding.etCountry.setText(UserObject.country)
        binding.etTown.setText(UserObject.town)
        binding.etAddress.setText(UserObject.address)
    }

    private fun setUpClickListeners(){

        // click on delivery block
        binding.rgDeliveryType.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                binding.rbDeliveryMan.id -> OrderObject.chooseDeliveryMan()
                binding.rbPostOffice.id -> OrderObject.choosePostOffice()
            }
        }

        //click on payment block
        binding.rgPaymentType.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                binding.rbCreditCard.id -> OrderObject.chooseCreditCard()
                binding.rbCash.id -> OrderObject.chooseCash()
            }
        }

        // btn CONFIRM
        binding.btnConfirm.setOnClickListener {

            if (!areFieldsEmpty()){

                viewModel.saveOrder(binding.etName.text.toString().trim { it <= ' ' },
                        binding.etSurname.text.toString().trim { it <= ' ' },
                        binding.etCountry.text.toString().trim { it <= ' ' },
                        binding.etTown.text.toString().trim { it <= ' ' },
                        binding.etAddress.text.toString().trim { it <= ' ' })
                binding.errorMessage.visibility = View.INVISIBLE
                APP_ACTIVITY.replaceFragment(EmptyBasketFragment())

            } else { binding.errorMessage.visibility = View.VISIBLE }
        }
    }

    private fun areFieldsEmpty(): Boolean{
        return binding.etName.text.trim { it <= ' ' }.isEmpty() ||
                binding.etSurname.text.trim { it <= ' ' }.isEmpty() ||
                binding.etCountry.text.trim { it <= ' ' }.isEmpty() ||
                binding.etTown.text.trim { it <= ' ' }.isEmpty() ||
                binding.etAddress.text.trim { it <= ' ' }.isEmpty() ||
                binding.rgDeliveryType.checkedRadioButtonId == -1 ||
                binding.rgPaymentType.checkedRadioButtonId == -1
    }
}