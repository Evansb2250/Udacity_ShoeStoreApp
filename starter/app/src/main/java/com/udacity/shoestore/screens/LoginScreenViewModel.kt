package com.udacity.shoestore.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginScreenViewModel : ViewModel() {

    //Stores all the users in the system
    private var users:HashMap<String, String> = HashMap<String, String>()

    // variables to hold data inserted in the editText field
    private var _userName= MutableLiveData<String>()
    private var _password= MutableLiveData<String>()
    private var _passwordConfirmation= MutableLiveData<String>()


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






    private var _validLoginRequest = MutableLiveData<Boolean>()
    var validLoginRequest:LiveData<Boolean> = _validLoginRequest
    set(value){
        field = _validLoginRequest
    }


    private var _logginButtonPressed = MutableLiveData<Boolean>()
    var logginButtonPressed:LiveData<Boolean> = _logginButtonPressed
    set(value){
        field =_logginButtonPressed
    }



    private var _createButtonPressed = MutableLiveData<Boolean>()
    var createButtonPressed:LiveData<Boolean> = _createButtonPressed
        set(value){
            field =_createButtonPressed
        }



    init {
        _createButtonPressed.value = false
        _loginState.value = false
        _logginButtonPressed.value = false
        _userName.value = ""
        _passwordConfirmation.value = ""
        _password.value = ""
    }


    fun creatingNewAccount(){
      _loginState.value = true
    }



    fun getUIDetails(nameEntry: String, passwordEntry: String, passwordConfirmEntry: String =""){
        //gets the information from GUI
        _userName.value = nameEntry
        _password.value = passwordEntry
        _passwordConfirmation.value = passwordConfirmEntry

        //Checks to see if the user needs to be added or logged in
        checkingRequest()
    }


    fun checkingRequest(){
        if(loginState.value == true){
            if(doesUserExist()){
                if(isPasswordValid()){
                    _validLoginRequest.value = true
                }else
                    alertUserRequestFailed()

            }else
                alertUserRequestFailed()

        }else
            createNewUser()
    }



    private fun alertUserRequestFailed() { _guiMessage.value = "Access Denied!!" }

    private fun createNewUser() {
        if(!doesUserExist() && doesPasswordsMatch() && _userName.value.toString() != ""){
            users.put(_userName.value.toString(),  _password.value.toString())
        }else{
            //creates an error message
                alertUserRequestFailed()
        }

    }



    private fun doesPasswordsMatch(): Boolean = if(_password.value.equals(_passwordConfirmation.value)) true else false

    private fun isPasswordValid():Boolean = users.get(_userName.value.toString())!!.get(0).equals(_password.value.toString())

    private fun doesUserExist() :Boolean =  users.containsKey(_userName.value)





    fun restoreLoginState(){ _loginState.value = false }


    fun clearVariableData(){
        _userName.value = ""
        _password.value = ""
        _passwordConfirmation.value = ""
    }


    fun logginButtonPressed(){ _logginButtonPressed.value = true }

    fun resetButtonState(state: Boolean) { _logginButtonPressed.value = state }

    fun createAccount(){ _createButtonPressed.value = true }
    fun resetCreateButton(){ _createButtonPressed.value = false }
}



