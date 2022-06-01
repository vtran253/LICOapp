package com.example.lico.eventsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lico.database.Events
import com.example.lico.databinding.FragmentEventsItemBinding
import com.example.lico.databinding.EventsItemBinding

/**
 * A RecyclerView adapter that uses the DiffCallback for better performance and a listener to handle
 * clicks/taps on each item.
 */
class EventsListAdapter(val clickListener: EventsListener) : ListAdapter<Events,
        EventsListAdapter.ItemViewHolder>(EventsDiffCallback()) {

    /**
     * Inner class used to store data about each element in the RecyclerView. We provide a binding
     * to make it easy to access elements of the layout.
     */
    class ItemViewHolder(val binding: EventsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Assign an Events object and clickListener to the ItemViewHolder
         */
        fun bind(item: Events, clickListener: EventsListener) {
            binding.events = item
            binding.clickListener = clickListener
        }
    }

    /**
     * Creates a View to visualize one element in the RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // We use an inflater based on the parent (EventsListFragment) and create an
        // ItemViewHolder with binding to the layout to simplify access.
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = EventsItemBinding.inflate(layoutInflater, parent, false)
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
class EventsDiffCallback : DiffUtil.ItemCallback<Events>() {
    /**
     * We use EventId because it is a unique ID referring to a single element.
     */
    override fun areItemsTheSame(oldItem: Events, newItem: Events): Boolean {
        return oldItem.eventsId == newItem.eventsId
    }

    /**
     * We check all properties to check equality between Events objects.
     */
    override fun areContentsTheSame(oldItem: Events, newItem: Events): Boolean {
        return oldItem.name == newItem.name && oldItem.description == newItem.description
    }
}

/**
 * Listener that accepts a lambda function clickListener. It will be called when an element of the
 * RecyclerView is clicked/tapped.
 */
class EventsListener(val clickListener: (EventsId: Long) -> Unit) {
    fun onClick(Events: Events) = clickListener(Events.eventsId)
}