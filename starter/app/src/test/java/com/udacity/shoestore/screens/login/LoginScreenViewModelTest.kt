package com.udacity.shoestore.screens.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.shoestore.dataStorage.Database
import com.udacity.shoestore.insertDataToLoginViewModel
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenViewModelTest : TestCase(){

/*
1. Test adding new user
2. test adding duplicate user
3. test login in with right credentials
4. test login with no credentials
5. test login with wrong credentials
6. test adding user with name no password
7. test adding user with password no name
8. test adding user with mismatching passwords
9.
 */



    @Test
    fun addUser_completeInfo_userAdded(){
        val viewModel = getLoginViewModel()
        viewModel.updateStateToRegistration()
        insertDataToLoginViewModel("samuel", "123", "123")
        viewModel.loginORCreateRequest()
        assertEquals(true, Database.userTable.containsUser("samuel"))
    }

    @Test
    fun addUser_addDuplicateUser_userAdded(){
        val viewModel = getLoginViewModel()
        viewModel.updateStateToRegistration()

        insertDataToLoginViewModel("samuel", "123", "123")


        val beforeCreatingUser = viewModel.doesUserExist()
        //Create User
        viewModel.loginORCreateRequest()

        //Attempt to create user with same name
        insertDataToLoginViewModel("samuel", "653", "653")

        //Create User
        viewModel.loginORCreateRequest()

        assertEquals(null, viewModel.guiMessage.value )
    }







    fun getLoginViewModel() :LoginScreenViewModel = LoginScreenViewModel()



}