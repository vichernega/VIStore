package com.example.vistore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vistore.databinding.UserOrdersRecyclerViewItemBinding
import com.example.vistore.objects.Order
import com.example.vistore.objects.OrderObject
import com.example.vistore.utilits.ADMINISTRATOR_ACTIVITY
import com.example.vistore.utilits.replaceFragment

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class UserOrdersRecyclerViewAdapter(private val orderList: List<Order>):
    RecyclerView.Adapter<UserOrdersRecyclerViewAdapter.UserOrdersViewHolder>() {

    /** initialize recycler item components*/
    class UserOrdersViewHolder(val binding: UserOrdersRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    /** inflates the recycler item to recycler view*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserOrdersViewHolder {
        return UserOrdersViewHolder(
            UserOrdersRecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /**sets data from productList to components in recyclerView_item*/
    override fun onBindViewHolder(holder: UserOrdersViewHolder, position: Int) {
        //show order id
        holder.binding.tvOrderId.text = orderList[position].orderId

        // on order click
        holder.binding.userOrdersRecyclerViewItem.setOnClickListener {
            OrderObject.setOrder(orderList[position])
            //ADMINISTRATOR_ACTIVITY.replaceFragment(OrderFragment)
        }
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}