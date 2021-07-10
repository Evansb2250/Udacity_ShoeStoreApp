package com.udacity.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.dataStorage.UserData

class LoginScreenViewModel(private val db: UserData) : ViewModel() {

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


    //triggers navigation to the welcome page
    private var _validLoginRequest = MutableLiveData<Boolean>()
    var validLoginRequest:LiveData<Boolean> = _validLoginRequest
    set(value){
        field = _validLoginRequest
    }


    fun creatingNewUserState(){ _loginState.value = false }

    fun restoreLoginState() {_loginState.value = true }



    fun getUIDetails(nameEntry: String, passwordEntry: String, passwordConfirmEntry: String =""){
        //gets the information from GUI
        _userName.value = nameEntry
        _password.value = passwordEntry
        _passwordConfirmation.value = passwordConfirmEntry

        //Checks to see if the user needs to be added or logged in
        checkingRequest()
    }



    fun checkingRequest(){
        val email = _userName.value.toString()
        val password  = _password.value.toString()

        if(loginState.value == true){
            if(db.containsUser(email) && db.isValidLogRequest(email, password)) _validLoginRequest.value = true else alertUserRequestFailed()
        }else
            createNewUser()
    }




    private fun alertUserRequestFailed() { _guiMessage.value = "Access Denied!!" }

    private fun createNewUser() {
        if(!doesUserExist() && doesPasswordsMatch() && _userName.value.toString() != ""){
             val email = _userName.value.toString()
             val password =_password.value.toString()

             db.createUser(email,password)
            _validLoginRequest.value = true
        }else{
            //creates an error message
            alertUserRequestFailed()

        }

    }


    private fun doesPasswordsMatch(): Boolean = if(_password.value.equals(_passwordConfirmation.value)) true else false


    private fun doesUserExist() :Boolean =  db.containsUser(_userName.value.toString())


    fun clearVariableData(){
        _userName.value = ""
        _password.value = ""
        _passwordConfirmation.value = ""
    }


    fun resetLoginState() {
        _validLoginRequest.value = false
    }



    init {
        _loginState.value = true
        _userName.value = ""
        _passwordConfirmation.value = ""
        _password.value = ""
    }


}








