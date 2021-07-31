package com.udacity.shoestore

import com.udacity.shoestore.globalVariables.AddShoeEditTextVar
import com.udacity.shoestore.globalVariables.LoginEditTextVar
import com.udacity.shoestore.screens.forms.ShoeFormViewModel

//Resets static variables
fun resetVariables(){
    AddShoeEditTextVar.clear()
    ShoeFormViewModel.resetBarCode()
}


//Function to added data to the global variables used for 2-way databinding
//This information is used to create a new shoe.
fun createShoe(name: String, company: String, size: String, desc: String = "") {
    AddShoeEditTextVar.shoeName = name
    AddShoeEditTextVar.companyName = company
    AddShoeEditTextVar.shoeDiscription = desc
    AddShoeEditTextVar.shoeSizeString = size
}


fun insertDataToLoginViewModel(email:String, password:String, passwordConfirm :String = ""){
    LoginEditTextVar.email = email
    LoginEditTextVar.password = password
    LoginEditTextVar.passwordAuthentication = passwordConfirm
}