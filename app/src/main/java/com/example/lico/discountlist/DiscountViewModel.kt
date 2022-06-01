package com.example.lico.discountlist

import android.app.Application
import androidx.lifecycle.*
import com.example.lico.database.Discount
import com.example.lico.database.DiscountDao
import kotlinx.coroutines.launch

/**
 * DiscountViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values.
 */
class DiscountViewModel(
    val database: DiscountDao, // Data access object for the Discount entity
    application: Application
) : AndroidViewModel(application) {


    var name = MutableLiveData("")
    var location = MutableLiveData("")
    var totalDiscount = MutableLiveData("")
    var couponCode = MutableLiveData("")

    // Retrieves all Discount objects from the database
    // Represented as a LiveData<List<Discount>>
    val discountList = database.getAllDiscounts()

    /**
     * Inserts the Discount object into the database.
     */
    fun insert() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Create Discount object using data stored in the EditText views

            var discount = Discount()
            discount.name = name.value.toString()
            discount.location = location.value.toString()
            discount.totalDiscount = totalDiscount.value.toString()
            discount.couponCode = couponCode.value.toString()

            // Insert data to the database using the insert coroutine.
            database.insert(discount)
        }
    }


    /**
     * Deletes all discount entities in the database.
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