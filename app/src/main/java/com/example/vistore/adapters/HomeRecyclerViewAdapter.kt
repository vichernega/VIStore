package com.example.vistore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.vistore.databinding.CommonRecyclerViewItemBinding
import com.example.vistore.fragments.GoodFragment
import com.example.vistore.fragments.HomeFragment
import com.example.vistore.objects.Good
import com.example.vistore.objects.GoodObject
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.replaceFragment

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class HomeRecyclerViewAdapter(private val productList: List<Good>) :
    RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder>() {

    /** initialize recycler item components*/
    class HomeViewHolder(val binding: CommonRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root)

    /** inflates the recycler item to recycler view*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            CommonRecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }
    /**sets data from productList to components in recyclerView_item*/
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.binding.productPhoto.load(productList[position].image_link) // using Coil to load images
        holder.binding.tvProductBrand.text = productList[position].brand.toUpperCase()
        holder.binding.tvProductName.text = productList[position].name
        holder.binding.tvProductType.text = productList[position].category
        holder.binding.tvProductPrice.text = productList[position].price + productList[position].price_sign

        // click listener for item in recyclerView
        holder.binding.commonRecyclerViewItem.setOnClickListener {
            GoodObject.set(productList[position])   // save item in GoodObject to show its data in GoodFragment
            APP_ACTIVITY.replaceFragment(GoodFragment())
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }



}