package com.udacity.shoestore.globalVariables

class EditTextVar {

    companion object {
        var shoeName: String = ""
        var companyName: String = ""
        var shoeSize: Double = 0.0
        var shoeDiscription: String = ""
        var shoeSizeString: String = ""


        fun clear(){
            shoeName = ""
            companyName = ""
            shoeSize = 0.0
            shoeDiscription = ""
            shoeSizeString = ""
        }
    }



}




