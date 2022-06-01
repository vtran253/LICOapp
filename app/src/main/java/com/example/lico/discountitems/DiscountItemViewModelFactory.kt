package com.example.lico.discountitems
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lico.database.DiscountDao
import com.example.lico.discountitems.DiscountItemViewModel

/**
 * Generates an IntersectionViewModel tied to the database. It uses the provided intersection ID to
 * to create the IntersectionItemViewModel.
 */
class DiscountItemViewModelFactory(
    private val intersectionId: Long,
    private val dataSource: DiscountDao, // Data access object
    private val application: Application
): ViewModelProvider.Factory {

    /**
     * Creates the DiscountViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiscountItemViewModel::class.java)) { // ViewModel class
            return DiscountItemViewModel(intersectionId, dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}