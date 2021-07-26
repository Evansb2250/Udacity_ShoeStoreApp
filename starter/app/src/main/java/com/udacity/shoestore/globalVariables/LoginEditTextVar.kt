package com.udacity.shoestore.globalVariables

//This class contains public variables used for 2-way binding in the
// Login fragment
class LoginEditTextVar {
    companion object{
        var email: String = ""
        var password: String = ""
        var passwordAuthentication: String = ""


    // clears the text in the login fragment.
        fun clear(){
             email  = ""
             password = ""
             passwordAuthentication = ""
        }
    }
}