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
import com.udacity.shoestore.globalVariables.EditTextVar
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.shoeListing.ShoeListingViewModel
import kotlinx.coroutines.flow.callbackFlow
import androidx.activity.OnBackPressedCallback as OnBackPressedCallback


/**
 * A simple [Fragment] subclass.
 * Use the [ShoeForm.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeForm : Fragment() {


    private lateinit var binding: FragmentShoeFormBinding
    private lateinit var viewModel: ShoeFormViewModel
    private val sharedViewModel:ShoeListingViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //overrides the onBackPress to erase any entry added by the user
        activity?.onBackPressedDispatcher?.addCallback(this, object:OnBackPressedCallback(true){
            override fun handleOnBackPressed(){
                EditTextVar.clear()
                findNavController().navigate(ShoeFormDirections.actionShoeFormToShoeListing())
            }
        })
    }


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
            viewModel.incrementBarCode()
            EditTextVar.clear()
            findNavController().navigate(ShoeFormDirections.actionShoeFormToShoeListing())
        }
    }

    }

