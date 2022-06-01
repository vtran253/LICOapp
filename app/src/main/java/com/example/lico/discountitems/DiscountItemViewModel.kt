package com.example.lico.discountitems

import android.app.Application
import androidx.lifecycle.*
import com.example.lico.database.DiscountDao
import com.example.lico.database.Discount
import kotlinx.coroutines.launch

/**
 * DiscountItemViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values. It retrieves the corresponding discount
 * with the provided discount ID.
 */
class DiscountItemViewModel(
    val discountId: Long,
    val database: DiscountDao, // Data access object for the Discount entity
    application: Application
) : AndroidViewModel(application) {

    // Retrieves a LiveData-wrapped discount object given its ID
    val discount = database.get(discountId)
}