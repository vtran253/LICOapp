package com.example.lico.discountlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lico.R
import com.example.lico.database.Discount
import com.example.lico.database.LicoDatabase
import com.example.lico.databinding.FragmentDiscountListBinding

/**
 * Fragment that displays the input text fields and Discount list
 */
class DiscountListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create data binding
        val binding: FragmentDiscountListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_discount_list, container, false)

        // Get reference to the application
        val application = requireNotNull(this.activity).application

        // Retrieve Discount data access object.
        val dataSource = LicoDatabase.getInstance(application).discountDao

        // Create a factory that generates DiscountViewModels connected to the database.
        val viewModelFactory = DiscountViewModelFactory(dataSource, application)

        // Generate an DiscountViewModel using the factory.
        val discountViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(DiscountViewModel::class.java)



        // Connect the DiscountViewModel with the variable in the layout
        binding.discountViewModel = discountViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        // Provide a lambda function that is called when the RecyclerView item is selected.
        var discountAdapter = DiscountListAdapter(DiscountListener {
                discountId ->
            // Navigate to the Discount view and provide the id of the Discount referenced
            // by the select RecyclerView item.

            this.findNavController().navigate(
                DiscountListFragmentDirections
                    .actionDiscountListFragmentToDiscountItemFragment(discountId)
//            action_discountListFragment_to_discountItemFragment
            )
        })
        // Attach discount adapter.
        binding.discountRecyclerview.adapter = discountAdapter

        // Submit an updated list to the discount listAdapter whenever it changes. Take note
        // discountList is a LiveData object.
        discountViewModel.discountList.observe(viewLifecycleOwner, Observer {
            it?.let {
                discountAdapter.submitList(it)
            }
        })
        return binding.root
    }
}