package com.example.vistore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vistore.databinding.MenuRecyclerViewItemBinding
import com.example.vistore.fragments.MenuResultFragment
import com.example.vistore.objects.Category
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.replaceFragment

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class MenuRecyclerViewAdapter(private val categoryList: List<Category>) :
    RecyclerView.Adapter<MenuRecyclerViewAdapter.MenuViewHolder>() {

    /** initialize recycler item components*/
    class MenuViewHolder(val binding: MenuRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    /** inflates the recycler item to recycler view*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            MenuRecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /**sets data from productList to components in recyclerView_item*/
    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {

        // show each category name
        holder.binding.categoryName.text = categoryList[position].toString()

        //on category click --> Result fragment
        holder.binding.menuRecyclerViewItem.setOnClickListener {
            APP_ACTIVITY.replaceFragment(MenuResultFragment(categoryList[position].requestName))
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}
