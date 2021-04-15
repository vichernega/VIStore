package com.example.vistore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vistore.databinding.UsersRecyclerViewItemBinding
import com.example.vistore.objects.User

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class UsersRecyclerViewAdapter(private val usersList: List<User>): RecyclerView.Adapter<UsersRecyclerViewAdapter.UsersViewHolder>() {

    /** initialize recycler item components*/
    class UsersViewHolder(val binding: UsersRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    /** inflates the recycler item to recycler view*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            UsersRecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /**sets data from productList to components in recyclerView_item*/
    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        // show user info
        holder.binding.tvUserName.text = "${usersList[position].name} ${usersList[position].surname}"

        // on user click
        holder.binding.usersRecyclerViewItem.setOnClickListener {
            //ADMINISTRATOR_ACTIVITY.replaceFragment(UserOrdersFragment())
        }

    }

    override fun getItemCount(): Int {
        return usersList.size
    }

}