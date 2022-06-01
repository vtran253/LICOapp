package com.example.lico.resourceList

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lico.database.ResourceDAO

class ResourceViewModelFactory (
    private val dataSource: ResourceDAO, // Data access object
    private val application: Application
    ) : ViewModelProvider.Factory {

        /**
         * Creates the ResourceViewModel
         */
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ResourceViewModel::class.java)) { // ViewModel class
                return ResourceViewModel(dataSource, application) as T // Call ViewModel constructor
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}