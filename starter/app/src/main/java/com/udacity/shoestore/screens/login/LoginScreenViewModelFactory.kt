package com.udacity.shoestore.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.dataStorage.UserData
import java.lang.IllegalArgumentException

class LoginScreenViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginScreenViewModel::class.java)){
            //Checks to see if an instance of LoginScreenViewModel has been created
                //return a new one if False or returns the previous instance.
            return LoginScreenViewModel(UserData()) as T
        }else
            throw IllegalArgumentException("unknown class")
    }
}