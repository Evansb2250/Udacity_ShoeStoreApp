package com.udacity.shoestore.screens.forms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeFormViewModel : ViewModel() {
    var shoeName : String =""
    var companyName : String = ""
    var shoeSize : Double = 0.0
    var shoeDiscription:String =""
    var shoeSizeString : String = ""

    private var _isFormRequirementsMet = MutableLiveData<Boolean>()
    var isformRequirementsMet: LiveData<Boolean> = _isFormRequirementsMet
            set(value){
                field = _isFormRequirementsMet
            }




    init {
        _isFormRequirementsMet.value = false
    }




    fun getShoe(): Shoe?{
        if(validateUserInput()) {
            shoeSize = shoeSizeString.toDouble()
            return Shoe(shoeName,shoeSize,companyName,shoeDiscription)
        }else{
            resetReqFlag()
        }
        return null
    }



    fun validateUserInput():Boolean{
       _isFormRequirementsMet.value = shoeName != "" || companyName != "" || shoeSizeString != ""
        return _isFormRequirementsMet.value!!
    }




    fun resetReqFlag(){
        _isFormRequirementsMet.value = false
    }

}