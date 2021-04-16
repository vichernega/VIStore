package com.example.vistore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.vistore.databinding.OrderRecyclerViewItemBinding
import com.example.vistore.objects.Good

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class OrderGoodRecyclerViewAdapter(private val productList: List<Good>) :
    RecyclerView.Adapter<OrderGoodRecyclerViewAdapter.OrderGoodViewHolder>() {

    /** initialize recycler item components*/
    class OrderGoodViewHolder(val binding: OrderRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    /** inflates the recycler item to recycler view*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderGoodViewHolder {
        return OrderGoodViewHolder(
            OrderRecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /**sets data from productList to components in recyclerView_item*/
    override fun onBindViewHolder(holder: OrderGoodViewHolder, position: Int) {

        // show all GoodObject info in fragment
        holder.binding.productPhoto.load(productList[position].image_link)
        holder.binding.tvProductFullName.text =
            " ${productList[position].brand.toUpperCase()} ${productList[position].name}"
        holder.binding.tvProductType.text = productList[position].product_type

        holder.binding.tvGoodAmount.text = productList[position].amount_basket
        holder.binding.tvGoodPrice.text =
            " ${productList[position].price}${productList[position].price_sign}"
        holder.binding.tvGoodTotalPrice.text =
            " ${productList[position].value_basket}${productList[position].price_sign}"

    }

    override fun getItemCount(): Int {
        return productList.size
    }
}