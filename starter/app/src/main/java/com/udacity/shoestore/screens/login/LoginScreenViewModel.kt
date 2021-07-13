package com.udacity.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.dataStorage.UserData



class LoginScreenViewModel(private val db: UserData) : ViewModel() {

    // variables to hold data inserted in the editText field
     var userName = MutableLiveData<String>()
     var password = MutableLiveData<String>()
     var passwordConfirmation = MutableLiveData<String>()


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


    fun creatingNewUserState(){ _loginState.value = SUBCRIBING }

    fun restoreLoginState() {_loginState.value = LOGGING_IN }



    fun getUIDetails(){
        //Checks to see if the user needs to be added or logged in
        checkingRequest()
    }



    fun checkingRequest(){
        if(loginState.value == LOGGING_IN){
            val email = userName.value.toString()
            val password  = password.value.toString()
            if(db.containsUser(email) && db.isValidLogRequest(email, password))
                _validLoginRequest.value = CREDENTIALS_ACCEPTED else alertUserRequestFailed()
        }else
            createNewUser()
    }


    private fun alertUserRequestFailed() {
        _guiMessage.value = if(loginState.value == LOGGING_IN) "could not log in!!" else "couldn't create user"
    }

    private fun createNewUser() {
        if(!doesUserExist() && doesPasswordsMatch() && userName.value.toString() != ""){
             val email = userName.value.toString()
             val password =password.value.toString()

             db.createUser(email,password)
            _validLoginRequest.value = CREDENTIALS_ACCEPTED
        }else{
            //creates an error message
            alertUserRequestFailed()

        }

    }


    private fun doesPasswordsMatch(): Boolean = if(password.value.equals(passwordConfirmation.value)) true else false


    private fun doesUserExist() :Boolean =  db.containsUser(userName.value.toString())


    fun clearVariableData(){
        userName.value = ""
        password.value = ""
        passwordConfirmation.value = ""
    }


    fun resetLoginState() {
        _validLoginRequest.value = !CREDENTIALS_ACCEPTED
    }



    init {
        _loginState.value = LOGGING_IN
        userName.value = ""
        passwordConfirmation.value = ""
        password.value = ""

    }
    companion object{
        private const val LOGGING_IN = true
        private const val SUBCRIBING = false
        private const val CREDENTIALS_ACCEPTED = true
    }


}








