package com.example.lico.resourceItems

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lico.database.ResourceDAO

import com.example.lico.resourceItems.ResourceItemViewModel

class ResourceItemViewModelFactory(
    private val resourceid: Long,
    private val dataSource: ResourceDAO, // Data access object
    private val application: Application
): ViewModelProvider.Factory {

    /**
     * Creates the ResourceViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResourceItemViewModel::class.java)) { // ViewModel class
            return ResourceItemViewModel(resourceid, dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}