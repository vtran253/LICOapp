package com.example.lico.discountlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lico.database.Discount
import com.example.lico.databinding.FragmentDiscountItemBinding
import com.example.lico.databinding.DiscountItemBinding

/**
 * A RecyclerView adapter that uses the DiffCallback for better performance and a listener to handle
 * clicks/taps on each item.
 */
class DiscountListAdapter(val clickListener: DiscountListener) : ListAdapter<Discount,
        DiscountListAdapter.ItemViewHolder>(DiscountDiffCallback()) {

    /**
     * Inner class used to store data about each element in the RecyclerView. We provide a binding
     * to make it easy to access elements of the layout.
     */
    class ItemViewHolder(val binding: DiscountItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Assign an Discount object and clickListener to the ItemViewHolder
         */
        fun bind(item: Discount, clickListener: DiscountListener) {
            binding.discount = item
            binding.clickListener = clickListener
        }
    }

    /**
     * Creates a View to visualize one element in the RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // We use an inflater based on the parent (DiscountListFragment) and create an
        // ItemViewHolder with binding to the layout to simplify access.
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DiscountItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    /**
     * The function is called whenever the RecyclerView displays a specific element. It provides
     * access to the ItemViewHolder and its position.
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Assign the corresponding element from the data and the click listener. Take note getItem
        // is a function provided by ListAdapter.
        holder.bind(getItem(position), clickListener)
    }
}

/**
 * Manages a RecyclerView list using the Eugene W. Myers's difference algorithm to reduce processing.
 */
class DiscountDiffCallback : DiffUtil.ItemCallback<Discount>() {
    /**
     * We use DiscountId because it is a unique ID referring to a single element.
     */
    override fun areItemsTheSame(oldItem: Discount, newItem: Discount): Boolean {
        return oldItem.discountId == newItem.discountId
    }

    /**
     * We check all properties to check equality between Discount objects.
     */
    override fun areContentsTheSame(oldItem: Discount, newItem: Discount): Boolean {
        return oldItem.name == newItem.name && oldItem.location == newItem.location
    }
}

/**
 * Listener that accepts a lambda function clickListener. It will be called when an element of the
 * RecyclerView is clicked/tapped.
 */
class DiscountListener(val clickListener: (DiscountId: Long) -> Unit) {
    fun onClick(Discount: Discount) = clickListener(Discount.discountId)
}