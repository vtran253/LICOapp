package com.example.lico.eventsList

import android.app.Application
import androidx.lifecycle.*
import com.example.lico.database.Events
import com.example.lico.database.EventsDao
import kotlinx.coroutines.launch

/**
 * EventsViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values.
 */
class EventsViewModel(
    val database: EventsDao, // Data access object for the Events entity
    application: Application
) : AndroidViewModel(application) {

    // Used to assign and retrieve name and location values from the interface.
    var name = MutableLiveData("")
    var description = MutableLiveData("")

    // Retrieves all Events objects from the database
    // Represented as a LiveData<List<Events>>
    val eventsList = database.getAllEvents()

    /**
     * Inserts the Events object into the database.
     */
    fun insert() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Create Events object using data stored in the EditText views
            var events = Events()
            events.name = name.value.toString()
            events.description = description.value.toString()

            // Insert data to the database using the insert coroutine.
            database.insert(events)
        }
    }

    /**
     * Deletes all events entities in the database.
     */
    fun clear() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Delete data from the database using the clear coroutine.
            database.clear()
        }
    }
}