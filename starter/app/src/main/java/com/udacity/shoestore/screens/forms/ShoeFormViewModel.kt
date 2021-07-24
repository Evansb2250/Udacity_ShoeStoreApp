package com.udacity.shoestore.screens.forms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.globalVariables.AddShoeEditTextVar
import com.udacity.shoestore.models.Shoe

class ShoeFormViewModel : ViewModel() {

    companion object {
        private var barCode = 0

        //FOR TESTING PURPOSES ONLY
        fun resetBarCode() {
            barCode = 0
        }
    }

    private var _isFormRequirementsMet = MutableLiveData<Boolean>()
    var isformRequirementsMet: LiveData<Boolean> = _isFormRequirementsMet
        set(value) {
            field = _isFormRequirementsMet
        }


    init {
        _isFormRequirementsMet.value = false
    }


    fun getShoe(): Shoe? {
        if (validateUserInput()) {
            AddShoeEditTextVar.shoeSize = AddShoeEditTextVar.shoeSizeString.toDouble()

            return Shoe(
                name = AddShoeEditTextVar.shoeName,
                size = AddShoeEditTextVar.shoeSize,
                company = AddShoeEditTextVar.companyName,
                description = AddShoeEditTextVar.shoeDiscription,
                uniqueId = barCode,
                shoeSizeString = AddShoeEditTextVar.shoeSize.toString()
            )
        } else {
            resetReqFlag()
        }

        return null
    }


    fun validateUserInput(): Boolean {
        _isFormRequirementsMet.value =
            AddShoeEditTextVar.shoeName != "" || AddShoeEditTextVar.companyName != "" || AddShoeEditTextVar.shoeSizeString != ""
        return _isFormRequirementsMet.value!!
    }


    fun incrementBarCode() {
        barCode += 1
    }


    fun resetReqFlag() {
        _isFormRequirementsMet.value = false
    }

}