package com.example.lico.eventsList

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lico.database.EventsDao

/**
 * Generates an EventsViewModel tied to the database.
 */
class EventsViewModelFactory(
    private val dataSource: EventsDao, // Data access object
    private val application: Application
) : ViewModelProvider.Factory {

    /**
     * Creates the EventsViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventsViewModel::class.java)) { // ViewModel class
            return EventsViewModel(dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}