package com.example.lico


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.lico.database.Discount
import com.example.lico.database.LicoDatabase
import com.example.lico.databinding.ActivityMainBinding
import kotlin.random.Random

/**
 * Main interface of the application
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create data binding and assign layout for the activity.
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Setup navigation controller and action bar.
        val navController = this.findNavController(R.id.nav_host)
        NavigationUI.setupActionBarWithNavController(this, navController)

        // Code for manually adding Discount entities to Database if needed
//        val dataSource2 = LicoDatabase.getInstance(application).discountDao
//        val discountsList = getResources().getStringArray(R.array.discountArray)
//        for (discounts in discountsList) {
//
//            val entity = discounts.split(":").toTypedArray()
//            var discount = Discount()
//            discount.discountId = Random.nextLong(100000, 9999999)
//            discount.name = entity[0]
//            discount.location = entity[1]
//            discount.totalDiscount = entity[2]
//            discount.couponCode = entity[3]
//
//            // Insert data to the database using the insert coroutine.
//            dataSource2.initialize(discount)
//        }
    }

    /**
     * Override the default implementation of the Up button so that it uses our
     * navController.
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host)
        return navController.navigateUp()
    }
}