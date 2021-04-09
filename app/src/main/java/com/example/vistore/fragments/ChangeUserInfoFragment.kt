package com.example.vistore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.vistore.R
import com.example.vistore.databinding.FragmentChangeUserInfoBinding
import com.example.vistore.viewmodels.ChangeUserInfoViewModel
import com.example.vistore.objects.User
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.replaceFragment
import com.example.vistore.utilits.showToast

class ChangeUserInfoFragment : Fragment(R.layout.fragment_change_user_info) {

    private lateinit var binding: FragmentChangeUserInfoBinding
    private val viewModel by viewModels<ChangeUserInfoViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentChangeUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //hide BNB
        APP_ACTIVITY.hideBottomNavBar()

        setUserInfoInEditTexts()
        setUpClickListeners()
        observeViewModel()

    }

    //observing mutableLiveData in ChangeUserInfoRepository
    private fun observeViewModel(){
        viewModel.boolMutableLiveData.observe(viewLifecycleOwner, Observer {
            showToast("Saved")
            replaceFragment(ProfileFragment())
        })
    }


    private fun setUpClickListeners() {

        // ON CONFIRM CLICK
        binding.btnConfirm.setOnClickListener {

            viewModel.saveUserData(binding.etName.text.toString().trim { it <= ' ' },
                                    binding.etSurname.text.toString().trim { it <= ' ' },
                                    binding.etCountry.text.toString().trim { it <= ' ' },
                                    binding.etTown.text.toString().trim { it <= ' ' },
                                    binding.etAddress.text.toString().trim { it <= ' ' })
        }
    }

    // show user info in edit texts
    fun setUserInfoInEditTexts(){
        binding.etName.setText(User.name)
        binding.etSurname.setText(User.surname)
        binding.etCountry.setText(User.country)
        binding.etTown.setText(User.town)
        binding.etAddress.setText(User.address)
    }


}