package com.example.lico.discountlist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lico.database.DiscountDao

/**
 * Generates an DiscountViewModel tied to the database.
 */
class DiscountViewModelFactory(
    private val dataSource: DiscountDao, // Data access object
    private val application: Application
) : ViewModelProvider.Factory {

    /**
     * Creates the DiscountViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiscountViewModel::class.java)) { // ViewModel class
            return DiscountViewModel(dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}