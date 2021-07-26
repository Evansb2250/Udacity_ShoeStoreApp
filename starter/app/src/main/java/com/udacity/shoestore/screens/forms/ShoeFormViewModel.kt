package com.udacity.shoestore.screens.forms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.globalVariables.AddShoeEditTextVar
import com.udacity.shoestore.models.Shoe

class ShoeFormViewModel : ViewModel() {

    companion object {
        // variable used for assigning unique ID's
        private var barCode = 0

        //FOR TESTING PURPOSES ONLY
        // used to run multiple test at one time and not have the previous static value
        // intefere with results.
        fun resetBarCode() {
            barCode = 0
        }
    }

    //used to notify the fragment when to navigate back to the shoeList fragment.
    private var _isFormRequirementsMet = MutableLiveData<Boolean>()

    //Live data Value that is wrapped by _isFormRequirementsMet
    //lets users see the value without being able to change it.
    var isformRequirementsMet: LiveData<Boolean> = _isFormRequirementsMet
        set(value) {
            field = _isFormRequirementsMet
        }


    init {
        //initializes the value to false to keep the fragment from going back to
        // the shoeListing fragment.
        _isFormRequirementsMet.value = false
    }


    fun getShoe(): Shoe? {
        //Checks to see if the required fields have suitable values.
        if (validateUserInput()) {
            //AddShoeEditTextVar.shoeSize = AddShoeEditTextVar.shoeSizeString.toDouble()

            //uses the data in the edit text to create and return an instance of Shoe.
            return Shoe(
                name = AddShoeEditTextVar.shoeName,
                size = AddShoeEditTextVar.shoeSizeString.toDouble(),
                company = AddShoeEditTextVar.companyName,
                description = AddShoeEditTextVar.shoeDiscription,
                uniqueId = barCode,
                shoeSizeString = AddShoeEditTextVar.shoeSizeString
            )
        } else {
            // if conditions aren't met the flag is set to false and nothing changes
            // in the SHoe Form Fragment
            resetReqFlag()
        }

        // of all else fails a null value is sent back and the application with no add the instance
        // to the shoe list.
        return null
    }


    // checkst too see if all the requirements are met and returns the boolean value
    fun validateUserInput(): Boolean {
        _isFormRequirementsMet.value =
            AddShoeEditTextVar.shoeName != "" || AddShoeEditTextVar.companyName != "" || AddShoeEditTextVar.shoeSizeString != ""
        return _isFormRequirementsMet.value!!
    }


    // increments the static variable
    fun incrementBarCode() {
        barCode += 1
    }


    // sets the mutableLiveData Variable to false.
    fun resetReqFlag() {
        _isFormRequirementsMet.value = false
    }

}