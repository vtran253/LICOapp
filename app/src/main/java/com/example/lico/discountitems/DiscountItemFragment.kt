package com.example.lico.discountitems

import android.R.attr
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lico.R
import com.example.lico.database.Discount
import com.example.lico.database.LicoDatabase
import com.example.lico.databinding.FragmentDiscountItemBinding


/**
 * Fragment that displays a single discount.
 */
class DiscountItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve arguments passed from the RecyclerView
        val args = DiscountItemFragmentArgs.fromBundle(
            requireArguments()
        )

        // Create data binding
        val binding: FragmentDiscountItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_discount_item, container, false)

        // Get reference to this application
        val application = requireNotNull(this.activity).application

        // Retrieve Discount data access object.
        val dataSource = LicoDatabase.getInstance(application).discountDao


        // Create a factory that generates an DiscountViewModel connected to the database. The
        // discountId passed from the RecyclerView is used to display the corresponding
        // information.
        val viewModelFactory =
            DiscountItemViewModelFactory(args.discountId, dataSource, application)

        // Generate an DiscountViewModel using the factory.
        val discountItemViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(DiscountItemViewModel::class.java)

        // Connect the DiscountViewModel with the variable in the layout
        binding.discountItemViewModel = discountItemViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        return binding.root
    }
}