package com.udacity.shoestore.dataStorage

class User {
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