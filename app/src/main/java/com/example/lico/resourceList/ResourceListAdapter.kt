package com.example.lico.resourceList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lico.database.ResourceEntity
import com.example.lico.databinding.FragmentResourceBinding

/**
 * A RecyclerView adapter that uses the DiffCallback for better performance and a listener to handle
 * clicks/taps on each item.
 */
class ResourceListAdapter(val clickListener: ResourceListener) : ListAdapter<ResourceEntity,
        ResourceListAdapter.ItemViewHolder>(ResourceDiffCallback()) {

    /**
     * Inner class used to store data about each element in the RecyclerView. We provide a binding
     * to make it easy to access elements of the layout.
     */
    class ItemViewHolder(val binding: FragmentResourceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Assign an Resource object and clickListener to the ItemViewHolder
         */
        fun bind(item: ResourceEntity, clickListener: ResourceListener) {
            binding.resource = item
            binding.clickListener = clickListener
        }
    }

    /**
     * Creates a View to visualize one element in the RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // We use an inflater based on the parent (ResourceListFragment) and create an
        // ItemViewHolder with binding to the layout to simplify access.
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentResourceBinding.inflate(layoutInflater, parent, false)
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
class ResourceDiffCallback : DiffUtil.ItemCallback<ResourceEntity>() {
    /**
     * We use ResourceId because it is a unique ID referring to a single element.
     */
    override fun areItemsTheSame(oldItem: ResourceEntity, newItem: ResourceEntity): Boolean {
        return oldItem.resourceid == newItem.resourceid
    }

    /**
     * We check all properties to check equality between Resource objects.
     */
    override fun areContentsTheSame(oldItem: ResourceEntity, newItem: ResourceEntity): Boolean {
        return oldItem.resourceName == newItem.resourceName && oldItem.resourceInfo == newItem.resourceInfo
    }
}

/**
 * Listener that accepts a lambda function clickListener. It will be called when an element of the
 * RecyclerView is clicked/tapped.
 */
class ResourceListener(val clickListener: (resourceId: Long) -> Unit) {
    fun onClick(resourceEntity: ResourceEntity) = clickListener(resourceEntity.resourceid)
}