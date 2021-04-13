package com.example.vistore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.vistore.databinding.CommonRecyclerViewItemBinding
import com.example.vistore.fragments.GoodFragment
import com.example.vistore.objects.Good
import com.example.vistore.objects.GoodObject
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.replaceFragment

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class CommonRecyclerViewAdapter(private val productList: List<Good>) :
    RecyclerView.Adapter<CommonRecyclerViewAdapter.CommonViewHolder>() {

    /** initialize recycler item components*/
    class CommonViewHolder(val binding: CommonRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root)

    /** inflates the recycler item to recycler view*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        return CommonViewHolder(
            CommonRecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }
    /**sets data from productList to components in recyclerView_item*/
    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {

        // nullable value check (api returns null in some cases)
        productList[position].image_link?.let { holder.binding.productPhoto.load(it) }
        productList[position].brand?.let { holder.binding.tvProductBrand.text = it.toUpperCase() }
        productList[position].name?.let { holder.binding.tvProductName.text = it }
        productList[position].category?.let { holder.binding.tvProductCategory.text = it }
        if (productList[position].price_sign != null){
                (productList[position].price + productList[position].price_sign)?.let { holder.binding.tvProductPrice.text = it }
        } else {
            (productList[position].price + "$")?.let { holder.binding.tvProductPrice.text = it }
        }

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