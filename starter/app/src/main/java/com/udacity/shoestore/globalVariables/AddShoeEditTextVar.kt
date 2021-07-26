package com.udacity.shoestore.globalVariables

//This class contains public variables used for 2-way binding in the
// Shoe Form fragment
class AddShoeEditTextVar {

    companion object {
        var shoeName = ""
        var companyName: String = ""
        var shoeDiscription: String = ""
        var shoeSizeString: String = ""

        //resets the values found in the editText Fields
        fun clear(){
            shoeName = ""
            companyName = ""
            shoeDiscription = ""
            shoeSizeString = ""
        }
    }



}




