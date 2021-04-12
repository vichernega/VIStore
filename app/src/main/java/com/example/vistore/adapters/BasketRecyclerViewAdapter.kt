package com.example.vistore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.vistore.databinding.BasketRecyclerViewItemBinding
import com.example.vistore.fragments.GoodFragment
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.objects.Good
import com.example.vistore.objects.GoodObject
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.replaceFragment

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class BasketRecyclerViewAdapter(private val productList: List<Good>) :
    RecyclerView.Adapter<BasketRecyclerViewAdapter.BasketViewHolder>() {

    /** initialize recycler item components*/
    class BasketViewHolder(val binding: BasketRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root)

    /** inflates the recycler item to recycler view*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder(
            BasketRecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /**sets data from productList to components in recyclerView_item*/
    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {

        // show all GoodObject info in fragment
        holder.binding.productPhoto.load(productList[position].image_link)
        holder.binding.tvProductFullName.text = " ${productList[position].brand.toUpperCase()} ${productList[position].name}"
        holder.binding.tvProductType.text = productList[position].product_type
        holder.binding.tvGoodPrice.text = " ${productList[position].value_basket}${productList[position].price_sign}"
        holder.binding.tvGoodAmount.text = productList[position].amount_basket

        // click on item -> GoodFragment
        holder.binding.basketRecyclerViewItem.setOnClickListener {
            GoodObject.set(productList[position])   // save item in GoodObject to show its data in GoodFragment
            APP_ACTIVITY.replaceFragment(GoodFragment())
        }

        /*holder.binding.btnClose.setOnLongClickListener {  }*/

        // click on "+"
        holder.binding.btnPlus.setOnClickListener {

            GoodObject.set(productList[position])                           // set current list item in GoodObject
            GoodObject.addOneMoreInBasket()                                 // add one item in basket

            // show in recyclerView new data
            holder.binding.tvGoodAmount.text = GoodObject.amount_basket
            holder.binding.tvGoodPrice.text = "${GoodObject.value_basket}${GoodObject.price_sign}"

            FirebaseObject.saveGoodInUsersBasket(GoodObject)                // save changes in DB
        }

        // click on "-"
        holder.binding.btnMinus.setOnClickListener {

            GoodObject.set(productList[position])                           // set current list item in GoodObject
            GoodObject.deleteOneFromBasket()                                // delete one item from basket

            // show in recyclerView new data
            holder.binding.tvGoodAmount.text = GoodObject.amount_basket
            holder.binding.tvGoodPrice.text = "${GoodObject.value_basket}${GoodObject.price_sign}"

            FirebaseObject.saveGoodInUsersBasket(GoodObject)                // save changes in DB
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}