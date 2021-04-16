package com.example.vistore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.vistore.R
import com.example.vistore.adapters.BasketRecyclerViewAdapter
import com.example.vistore.databinding.FragmentBasketBinding
import com.example.vistore.objects.Good
import com.example.vistore.utilits.replaceFragment
import com.example.vistore.viewmodels.BasketViewModel


class BasketFragment : Fragment(R.layout.fragment_basket) {

    private lateinit var binding: FragmentBasketBinding
    private val viewModel by viewModels<BasketViewModel>()
    private lateinit var recyclerViewAdapter: BasketRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentBasketBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.retrieveGoodsListFromBasket()
        observeViewModel()
        setUpClickListeners()
    }

    fun observeViewModel(){
        viewModel.basketGoodsList.observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)
        })
    }

    fun initRecyclerView(list: List<Good>){
        recyclerViewAdapter = BasketRecyclerViewAdapter(list)
        binding.basketRecyclerView.adapter = recyclerViewAdapter
    }

    fun setUpClickListeners(){
        binding.btnOrder.setOnClickListener { replaceFragment(MakeOrderFragment()) }
    }
}