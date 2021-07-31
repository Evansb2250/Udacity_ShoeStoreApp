package com.udacity.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.constants.*
import com.udacity.shoestore.dataStorage.Database

import com.udacity.shoestore.globalVariables.LoginEditTextVar


class LoginScreenViewModel() : ViewModel() {


    //flags for edit text to clear
    private var _clearEditText = MutableLiveData<Boolean>()
    var clearEditText: LiveData<Boolean> = _clearEditText
        set(value) {
            field = _clearEditText
        }


    //Message To User
    private var _guiMessage = MutableLiveData<String?>()
    var guiMessage: LiveData<String?> = _guiMessage
        set(value) {
            field = _guiMessage
        }


    //Watches the state of the login page
    private var _loginState = MutableLiveData<Boolean>()
    var loginState: LiveData<Boolean> = _loginState
        set(value) {
            field = _loginState
        }


    //triggers navigation to the welcome page
    private var _validLoginRequest = MutableLiveData<Boolean>()
    var validLoginRequest: LiveData<Boolean> = _validLoginRequest
        set(value) {
            field = _validLoginRequest
        }


    //Makes it so that a user can register a new user by making the invicible textViews visible for adding confirmation password.
    fun updateStateToRegistration() {
        _loginState.value = REGISTERING
    }

    //Makes it where a user can only login
    fun restoreLoginState() {
        _loginState.value = LOGGING_IN
    }

    // Clears LoginEditTextVar and it also clears the global values
    fun startRegistration() {
        updateStateToRegistration(); LoginEditTextVar.clear(); setClearEditTextToTrue()
    }

    //Removes data entered in Edit Text and returns back to the login State
    fun cancelRegistration() {
        restoreLoginState(); LoginEditTextVar.clear(); setClearEditTextToTrue()
    }

    //function that is activated when a user attempts to add a new user or log in
    fun getUIDetails() {
        //Checks to see if the user needs to be added or logged in
        loginOrSignUpRequest()
    }


    fun loginOrSignUpRequest() {
        //if user is attempting to login the information information from the 2-way data binding variables
        // is collected and the checked to see if it exist in a HashMap using the Singleton Pattern.
        if (loginState.value == LOGGING_IN) {
            logUserIn()
        } else { signUpRequest()    //attempts to create a new user login

        }
        //clears values in 2-way data Binding variables and edit Text
        setClearEditTextToTrue()
        LoginEditTextVar.clear()
    }

    private fun logUserIn() {
        if (Database.userTable.containsUser(LoginEditTextVar.email) && Database.userTable.isValidLogRequest(
                LoginEditTextVar.email, LoginEditTextVar.password
            )
        )
            _validLoginRequest.value = CREDENTIALS_ACCEPTED else alertUserRequestFailed()
    }


    fun guiMessageSent() {
        _guiMessage.value = null
    }

    //flags to GUI when to erase entries entered in the edit text
    fun setClearEditTextToTrue() {
        _clearEditText.value = true
    }

    //flags to GUI when the user fails to login or create a new account
    private fun alertUserRequestFailed() {
        _guiMessage.value = if (loginState.value == LOGGING_IN) LOGIN_FAILED else SIGNUP_FAIL
    }

    //checks to see if the passwords exist and that the user name does not already exist before
    //adding the new user to the hashMap.
    private fun signUpRequest() {
        if (!doesUserExist() && doesPasswordsMatch() && LoginEditTextVar.email != "") {
            Database.userTable.createUser(LoginEditTextVar.email, LoginEditTextVar.password)
            _validLoginRequest.value = CREDENTIALS_ACCEPTED
        } else {
            //creates an error message
            alertUserRequestFailed()

        }

    }


    // Checks to see if the password matches
    fun doesPasswordsMatch(): Boolean =
        LoginEditTextVar.password.equals(LoginEditTextVar.passwordAuthentication) && !LoginEditTextVar.password
            .trim().equals("")

    //Checks to see if the user already exist before adding to the hashMap
    fun doesUserExist(): Boolean = Database.userTable.containsUser(LoginEditTextVar.email)


    //resets the _validLoginRequest to false since credentials were not met
    fun resetLoginState() {
        _validLoginRequest.value = !CREDENTIALS_ACCEPTED
    }


    fun setClearEditToFalse() {
        _clearEditText.value = false
    }


    init {
        //starts the GUI in the Login state meaning only email and password edit text are shown.
        _loginState.value = LOGGING_IN
        _guiMessage.value = null
        setClearEditToFalse()
    }


}








