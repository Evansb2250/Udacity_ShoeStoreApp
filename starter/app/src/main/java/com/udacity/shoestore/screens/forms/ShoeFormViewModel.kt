package com.udacity.shoestore.screens.forms

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.globalVariables.EditTextVar
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeFormViewModel : ViewModel() {

    companion object {
        private var barCode = 0

        //FOR TESTING PURPOSES ONLY
        fun resetBarCode(){
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
            EditTextVar.shoeSize = EditTextVar.shoeSizeString.toDouble()

            return Shoe(
                name = EditTextVar.shoeName, size = EditTextVar.shoeSize,
                company = EditTextVar.companyName,
                description = EditTextVar.shoeDiscription,
                uniqueId = barCode,
                shoeSizeString = EditTextVar.shoeSize.toString()
            )
        } else {
            resetReqFlag()
        }

        return null
    }


    fun validateUserInput(): Boolean {
        _isFormRequirementsMet.value =
            EditTextVar.shoeName != "" || EditTextVar.companyName != "" || EditTextVar.shoeSizeString != ""
        return _isFormRequirementsMet.value!!
    }


    fun incrementBarCode() {
        barCode += 1
    }


    fun resetReqFlag() {
        _isFormRequirementsMet.value = false
    }

}