package com.udacity.shoestore.globalVariables

class LoginEditTextVar {
    companion object{
        var email: String = ""
        var password: String = ""
        var passwordAuthentication: String = ""



        fun clear(){
             email  = ""
             password = ""
             passwordAuthentication = ""
        }
    }
}