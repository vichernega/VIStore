package com.example.vistore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.load
import com.example.vistore.R
import com.example.vistore.databinding.FragmentGoodBinding
import com.example.vistore.objects.GoodObject
import com.example.vistore.utilits.showToast
import com.example.vistore.viewmodels.GoodViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class GoodFragment : Fragment(R.layout.fragment_good) {

    private lateinit var binding: FragmentGoodBinding
    private val viewModel by viewModels<GoodViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGoodBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showGoodData()
        observeViewModel()
        //checkIsGoodAlreadyInBasket()
        setUpOnClickListeners()

        //APP_ACTIVITY.hideBottomNavBar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIsGoodAlreadyInBasket()
    }

    fun showGoodData() {
        binding.goodImage.load(GoodObject.image_link)
        binding.tvGoodBrand.text = GoodObject.brand.toUpperCase()
        binding.tvGoodName.text = GoodObject.name
        binding.tvGoodProductType.text = GoodObject.product_type
        binding.tvGoodPrice.text = GoodObject.price + GoodObject.price_sign
        binding.tvGoodDescription.text = GoodObject.description
    }


    fun observeViewModel() {
        viewModel.isGoodInBasketLiveData.observe(viewLifecycleOwner, Observer { goodInBasket ->
            //Log.d("FIRESTOREdb", "VIEW:   isGoodInBasket = $goodInBasket")
            // show button DELETE
            if (goodInBasket) {
                // Log.d("FIRESTOREdb", "VIEW TRUE:   isGoodInBasket = $goodInBasket")
                binding.btnAddToBasket.visibility = View.INVISIBLE
                binding.btnDeleteFromBasket.visibility = View.VISIBLE
            }
            // show button ADD
            else {
                //  Log.d("FIRESTOREdb", "VIEW FALSE:   isGoodInBasket = $goodInBasket")
                binding.btnAddToBasket.visibility = View.VISIBLE
                binding.btnDeleteFromBasket.visibility = View.INVISIBLE
            }
        })
    }

    fun setUpOnClickListeners() {

        // btn ADD TO BASKET
        binding.btnAddToBasket.setOnClickListener {
            if (Firebase.auth.currentUser != null) {
                viewModel.addToBasket()
                showToast("Saved successfully!")
            } else {
                showToast("You don't have an account. Register!")
            }
        }

        // btn DELETE FROM BASKET
        binding.btnDeleteFromBasket.setOnClickListener {
            if (Firebase.auth.currentUser != null) {
                showToast("Deleted successfully")
                viewModel.deleteFromBasket()
            }
        }
    }

    fun checkIsGoodAlreadyInBasket() {
        if (Firebase.auth.currentUser != null) {
            viewModel.checkIsGoodAlreadyInBasket()
        }
    }
}
