package com.udacity.shoestore.screens.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.shoestore.constants.SIGNUP_FAIL
import com.udacity.shoestore.dataStorage.Database
import com.udacity.shoestore.globalVariables.LoginEditTextVar
import com.udacity.shoestore.insertDataToLoginViewModel
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenViewModelTest : TestCase(){

/*

3. test login in with right credentials
4. test login with no credentials
5. test login with wrong credentials
6. test adding user with name no password
7. test adding user with password no name
8. test adding user with mismatching passwords
9.
 */



    @Test
//    1. Test adding new user
    fun addUser_completeInfo_userAdded(){
        val viewModel = getLoginViewModel()
        viewModel.updateStateToRegistration()
        insertDataToLoginViewModel("samuel", "123", "123")
        viewModel.loginOrSignUpRequest()
        assertEquals(true, Database.userTable.containsUser("samuel"))
    }

    @Test
//    2. test adding duplicate user
    fun addUser_addDuplicateUser_userAdded(){
        val viewModel = getLoginViewModel()
        viewModel.updateStateToRegistration()
        insertDataToLoginViewModel("samuel", "123", "123")
        //Creates User
        viewModel.loginOrSignUpRequest()
        //Tries to add the user again to the database
        viewModel.loginOrSignUpRequest()
        assertEquals(SIGNUP_FAIL, viewModel.guiMessage.value )
    }








    fun getLoginViewModel() :LoginScreenViewModel = LoginScreenViewModel()



}