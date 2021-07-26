package com.udacity.shoestore.screens.forms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeFormBinding
import com.udacity.shoestore.globalVariables.AddShoeEditTextVar
import com.udacity.shoestore.screens.shoeListing.ShoeListingViewModel



class ShoeForm : Fragment() {

    //Databinding object used to connect xml views without using searchBy
    private lateinit var binding: FragmentShoeFormBinding
    //contains the logic for getting information from the GUI
    // and validating if the form is completed.
    private lateinit var viewModel: ShoeFormViewModel
    //used to gain access to the addShoeToList method in the ShoeList viewModel
    private val sharedViewModel: ShoeListingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Instantiates the databining object
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_form, container, false)
        // uses viewModelProvider to instantiate the ShoeFormViewModel
        // So data will be maintained when changing screen orientations
        viewModel = ViewModelProvider(this).get(ShoeFormViewModel::class.java)

        //Connects the ShoeFormViewModel with the viewModel in the XML layout.
        //which specifies which instance to observe and make changes too.
        binding.shoeViewModel = viewModel
        //sets the lifeCycle of the binding object to the current fragment
        binding.setLifecycleOwner(this)

        setUpFlagObserver()

        //returns root to inflate views into objects.
        return binding.root
    }


    private fun setUpFlagObserver() {
        //once a shoe is successfully added the isFormRequirementsMet liveData obj value will
        // change to true. This will prompt for the navigator to go back to the shoeList Fragment.
        viewModel.isformRequirementsMet.observe(viewLifecycleOwner, Observer { flag ->
            if (flag) {
                addShoeToNavigationArg()
                //resets value to false so that when the user selects to add another shoe
                // they aren't automatically navigatted back to the shoeList Fragment.
                viewModel.resetReqFlag()
            }
        })
    }


    private fun addShoeToNavigationArg() {
        // gets a instance of shoe
        val shoeObj = viewModel.getShoe()
        //Checks to see if it is null
        if (shoeObj != null) {
            //adds the instance to the shoelist using the shared viewModel
            sharedViewModel.addToInventory(shoeObj)
            //increments the barCode variable to prevent future shoe instances from haveing
            // The same id.
            viewModel.incrementBarCode()
            //clears the information from the Edit view.
            AddShoeEditTextVar.clear()
            // Navigates back to the shoeList fragment.
            findNavController().navigate(ShoeFormDirections.actionShoeFormToShoeListing())
        }
    }
}

