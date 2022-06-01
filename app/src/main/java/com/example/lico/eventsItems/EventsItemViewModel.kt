package com.example.lico.eventsItems

import android.app.Application
import androidx.lifecycle.*
import com.example.lico.database.EventsDao
import com.example.lico.database.Events
import kotlinx.coroutines.launch


class EventsItemViewModel(
    val eventsId: Long,
    val database: EventsDao, // Data access object for the Events entity
    application: Application
) : AndroidViewModel(application) {

    // Retrieves a LiveData-wrapped Events object given its ID
    val events = database.get(eventsId)
}