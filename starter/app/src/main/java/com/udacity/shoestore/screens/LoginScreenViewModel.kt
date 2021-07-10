package com.udacity.shoestore.screens

import androidx.lifecycle.*

class LoginScreenViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private var users:HashMap<String, String> = HashMap<String, String>()


    var userName = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var passwordConfirmation= MutableLiveData<String>()


    private var _loginState= MutableLiveData<Boolean>()
    var loginState:LiveData<Boolean> = _loginState
        set(value) {
            field = _loginState
        }


    init {
        _loginState.value = false
        userName.value = ""
        passwordConfirmation.value = ""
        password.value = ""
    }




/*
1. press button
2. call function to change live data
3.
 */

    fun creatingNewAccount(){
      _loginState.value = true
    }



    fun restoreLoginState(){
        _loginState.value = false
    }


    fun clearVariableData(){
        userName.value = ""
        password.value = ""
        passwordConfirmation.value = ""
    }


}






