package com.example.lico.resourceItems


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lico.MainActivity
import com.example.lico.R
import com.example.lico.database.LicoDatabase
import com.example.lico.database.ResourceEntity
import com.example.lico.databinding.FragmentDiscountItemBinding
import com.example.lico.databinding.FragmentResourceBinding
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class Resource:Fragment(R.layout.fragment_resource) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentResourceBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_resource, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = context as MainActivity
        val centerList = resources.getStringArray(R.array.resourceArray)

        val lv = context.findViewById(R.id.resourceListView) as ListView
        val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, centerList)
        lv.adapter = adapter
    }


}