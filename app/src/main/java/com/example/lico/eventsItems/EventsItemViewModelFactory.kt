package com.example.lico.eventsItems
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lico.database.EventsDao
import com.example.lico.eventsItems.EventsItemViewModel

/**
 * Generates an IntersectionViewModel tied to the database. It uses the provided intersection ID to
 * to create the IntersectionItemViewModel.
 */
class EventsItemViewModelFactory(
    private val intersectionId: Long,
    private val dataSource: EventsDao, // Data access object
    private val application: Application
): ViewModelProvider.Factory {

    /**
     * Creates the EventsViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventsItemViewModel::class.java)) { // ViewModel class
            return EventsItemViewModel(intersectionId, dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}