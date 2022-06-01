package com.example.lico.resourceItems

import android.app.Application
import androidx.lifecycle.*
import com.example.lico.database.ResourceEntity
import com.example.lico.database.ResourceDAO
import kotlinx.coroutines.launch

/**
 * DiscountItemViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values. It retrieves the corresponding discount
 * with the provided discount ID.
 */
class ResourceItemViewModel(
    private val resourceID: Long,
    val database: ResourceDAO, // Data access object for the ResourceEntity
    application: Application
) : AndroidViewModel(application) {

    // Retrieves a LiveData-wrapped discount object given its ID
    val resource = database.get(resourceID)
}