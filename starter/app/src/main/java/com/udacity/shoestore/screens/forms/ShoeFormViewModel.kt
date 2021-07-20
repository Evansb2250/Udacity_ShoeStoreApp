package com.udacity.shoestore.screens.forms

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.globalVariables.EditTextVar
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeFormViewModel : ViewModel() {

    companion object{
        private var barCode = 0
    }
//    var shoeName : String =""
//    var companyName : String = ""
//    var shoeSize : Double = 0.0
//    var shoeDiscription:String =""
//    var shoeSizeString : String = ""


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
            EditTextVar.shoeSize = EditTextVar.shoeSizeString.toDouble()
            return Shoe(name = EditTextVar.shoeName, size = EditTextVar.shoeSize,
                        company = EditTextVar.companyName,description = EditTextVar.shoeDiscription,
                        uniqueId = barCode)
        }else{
            resetReqFlag()
        }

        return null
    }


    fun validateUserInput():Boolean{
       _isFormRequirementsMet.value = EditTextVar.shoeName != "" || EditTextVar.companyName != "" || EditTextVar.shoeSizeString != ""
        return _isFormRequirementsMet.value!!
    }



    fun incrementBarCode(){
        barCode += 1
    }


    fun resetReqFlag(){
        _isFormRequirementsMet.value = false
    }

}