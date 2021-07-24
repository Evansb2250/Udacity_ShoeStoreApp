package com.udacity.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.constants.CREDENTIALS_ACCEPTED
import com.udacity.shoestore.constants.LOGGING_IN
import com.udacity.shoestore.constants.REGISTERING
import com.udacity.shoestore.dataStorage.Database

import com.udacity.shoestore.globalVariables.LoginEditTextVar
import com.udacity.shoestore.doesPasswordsMatch
import com.udacity.shoestore.doesUserExist


class LoginScreenViewModel() : ViewModel() {


    //flags for edit text to clear
    private var _clearEditText = MutableLiveData<Boolean>()
    var clearEditText:LiveData<Boolean> = _clearEditText
        set(value) {
            field = _clearEditText
        }


    //Message To User
    private var _guiMessage= MutableLiveData<String>()
    var guiMessage:LiveData<String> = _guiMessage
        set(value) {
            field = _guiMessage
        }


    //Watches the state of the login page
    private var _loginState= MutableLiveData<Boolean>()
    var loginState:LiveData<Boolean> = _loginState
        set(value) {
            field = _loginState
        }


    //triggers navigation to the welcome page
    private var _validLoginRequest = MutableLiveData<Boolean>()
    var validLoginRequest:LiveData<Boolean> = _validLoginRequest
    set(value){
        field = _validLoginRequest
    }



    fun updateStateToRegistration(){ _loginState.value = REGISTERING }

    fun restoreLoginState() {_loginState.value = LOGGING_IN }


    fun startRegistration(){ updateStateToRegistration() ; LoginEditTextVar.clear() ; setClearEditTextToTrue() }

    fun cancelRegistration(){ restoreLoginState() ; LoginEditTextVar.clear() ; setClearEditTextToTrue() }


    fun getUIDetails(){
        //Checks to see if the user needs to be added or logged in
        checkingRequest()
    }

    fun checkingRequest(){
        if(loginState.value == LOGGING_IN){
            if(Database.userTable.containsUser(LoginEditTextVar.email) && Database.userTable.isValidLogRequest(
                    LoginEditTextVar.email, LoginEditTextVar.password))
                _validLoginRequest.value = CREDENTIALS_ACCEPTED else alertUserRequestFailed()
        }else { createNewUser() }

        setClearEditTextToTrue()
        LoginEditTextVar.clear()
    }


    fun setClearEditTextToTrue(){
        _clearEditText.value = true
    }

    private fun alertUserRequestFailed() {
        _guiMessage.value = if(loginState.value == LOGGING_IN) "could not log in!!" else "couldn't create user"
    }

    private fun createNewUser() {
        if(!doesUserExist() && doesPasswordsMatch() && LoginEditTextVar.email != ""){
              Database.userTable.createUser(LoginEditTextVar.email, LoginEditTextVar.password)
             _validLoginRequest.value = CREDENTIALS_ACCEPTED
        }else{
            //creates an error message
            alertUserRequestFailed()

        }

    }

    fun resetLoginState() { _validLoginRequest.value = !CREDENTIALS_ACCEPTED }

    fun setClearEditToFalse() { _clearEditText.value = false }


    init {
        _loginState.value = LOGGING_IN
         setClearEditToFalse()
    }


}








