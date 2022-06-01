package com.example.lico.resourceList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lico.database.ResourceDAO
import com.example.lico.database.ResourceEntity
import kotlinx.coroutines.launch

class ResourceViewModel(
    val database: ResourceDAO, // Data access object for the Intersection entity
    application: Application
) : AndroidViewModel(application) {

    // Used to assign and retrieve name and location values from the interface.
    var name = MutableLiveData("")
    var location = MutableLiveData("")

    // Retrieves all Intersection objects from the database
    // Represented as a LiveData<List<Intersection>>
    val intersectionList = database.getAllResources()

    /**
     * Inserts the Intersection object into the database.
     */
    fun insert() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Create Intersection object using data stored in the EditText views
            var resource = ResourceEntity()
            resource.resourceName = name.value.toString()
            resource.resourceInfo = location.value.toString()

            // Insert data to the database using the insert coroutine.
            database.insert(resource)
        }
    }
}