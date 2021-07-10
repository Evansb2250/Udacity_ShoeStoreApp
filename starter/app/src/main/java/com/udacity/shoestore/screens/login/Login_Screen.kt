package com.udacity.shoestore.screens.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginScreenFragmentBinding
import kotlinx.android.synthetic.main.activity_main.*

//TODO create email format validation
//TODO create constraints for passwords

class Login_Screen : Fragment() {

    private lateinit var viewModelFactory: LoginScreenViewModelFactory
    private lateinit var binding: LoginScreenFragmentBinding
    private lateinit var viewModel: LoginScreenViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.login__screen_fragment, container, false)

        //initializes the viewModel, factory obj & Binds with the binding object
        setUpViewModel()


        //sets onClick listeners for the buttons
        intializeButtons()

        //Displays errors
        viewModel.guiMessage.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })


        //Switches between the state
        viewModel.loginState.observe(viewLifecycleOwner, Observer { inLoginState ->
            if (inLoginState) {
                changeLoginCreateButtonsVisibility(View.VISIBLE)
                createSubscriptionViews(View.INVISIBLE)
            }
            else {
                changeLoginCreateButtonsVisibility(View.INVISIBLE)
                createSubscriptionViews(View.VISIBLE)
            }
        })


        //responds when a valid user name and password is entered
        viewModel.validLoginRequest.observe(viewLifecycleOwner, Observer { requestResponse ->
            if(requestResponse) {
                logUserIn()
                viewModel.resetLoginState()
            }
        })




        return binding.root
    }


    private fun setUpViewModel() {
        viewModelFactory = LoginScreenViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginScreenViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
    }


    private fun intializeButtons() {
        setUpButtonListener(binding.loginButton)
        setUpButtonListener(binding.signUpButton)
        binding.cancelButton?.let { setUpButtonListener(it) }
        binding.createButton?.let { setUpButtonListener(it) }
    }




    private fun setUpButtonListener(button: Button){
        button.setOnClickListener{
            when(button){
                //attempt to log a user in
                binding.loginButton -> {getDataFromGUI()}
                //ask user for to enter username, password and validate password
                binding.signUpButton -> {
                    viewModel.creatingNewUserState()
                    viewModel.clearVariableData()
                    clearEditTexts()
                }
                //return to login state
                binding.cancelButton ->{
                    viewModel.restoreLoginState()
                    viewModel.clearVariableData()
                    clearEditTexts()
                }
                //Creating an account
                else ->{ getDataFromGUI()}
            }

        }


    }




    private fun clearEditTexts(){
            binding.userNameEdit.setText("")
            binding.passwordEdit.setText("")
            binding.confirmPasswordEdit.setText("")
    }


    private fun getDataFromGUI(){
        val name = binding.userNameEdit.getText().toString()?:""
        val password = binding.passwordEdit.getText().toString()?:""
        val passwordConfirmation =binding.confirmPasswordEdit.getText().toString()?:""
        viewModel.getUIDetails(name, password, passwordConfirmation)
    }


    private fun changeLoginCreateButtonsVisibility(visibility: Int) {
        binding.signUpButton?.visibility = visibility
        binding.loginButton?.visibility = visibility
    }

    private fun createSubscriptionViews(visibility: Int) {
        binding.confirmPasswordEdit?.visibility = visibility
        binding.confirmPasswordText?.visibility = visibility
        binding.cancelButton?.visibility = visibility
        binding.createButton?.visibility = visibility
    }

    private fun logUserIn(){
        findNavController().navigate(Login_ScreenDirections.actionLoginScreenToWelcomeScreen())
    }

}