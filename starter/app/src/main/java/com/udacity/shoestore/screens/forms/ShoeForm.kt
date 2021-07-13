package com.udacity.shoestore.screens.forms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeFormBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.shoeListing.ShoeListingViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ShoeForm.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeForm : Fragment() {

    private lateinit var binding: FragmentShoeFormBinding
    private lateinit var viewModel: ShoeFormViewModel
    private val sharedViewModel:ShoeListingViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Create a the binding object
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_form, container, false)

        viewModel = ViewModelProvider(this).get(ShoeFormViewModel::class.java)

        binding.shoeViewModel = viewModel
        binding.setLifecycleOwner(this)


        setUpFlagObserver()
        return binding.root
    }


    private fun setUpFlagObserver() {
        viewModel.isformRequirementsMet.observe(viewLifecycleOwner, Observer { flag ->
            if (flag) {
                addShoeToNavigationArg()
                viewModel.resetReqFlag()
            }
        })
    }


    private fun addShoeToNavigationArg() {
        val shoeObj = viewModel.getShoe()
        if (shoeObj != null) {
            sharedViewModel.addToInventory(shoeObj)
            findNavController().navigate(ShoeFormDirections.actionShoeFormToShoeListing())
        }
    }

    }

