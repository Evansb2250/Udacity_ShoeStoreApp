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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R


import com.udacity.shoestore.databinding.LoginScreenFragmentBinding
import kotlinx.android.synthetic.main.activity_main.*

//TODO create email format validation
//TODO create constraints for passwords

class Login_Screen : Fragment() {

    // private lateinit var viewModelFactory: LoginScreenViewModelFactory
    private lateinit var binding: LoginScreenFragmentBinding
    private lateinit var viewModel: LoginScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Instantiate the data binding object
        binding = DataBindingUtil.inflate(inflater, R.layout.login__screen_fragment, container, false)

        //
        val factoryObj = LoginScreenViewModelFactory()

        //initializes the viewModel, factory obj & Binds with the binding object
        viewModel = ViewModelProvider(this, factoryObj).get(LoginScreenViewModel::class.java)
        //setUpViewModel()
        binding.viewModel = viewModel

        //sets lifecycle to the binding object to the fragment
        binding.setLifecycleOwner(this)

        //Displays errors
        viewModel.guiMessage.observe(viewLifecycleOwner, Observer { message ->
            message?.let {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                //reset error message
                viewModel.guiMessageSent()
            }

        })


        //Switches between the state
        viewModel.loginState.observe(viewLifecycleOwner, Observer { inLoginState ->
            if (inLoginState) {
                changeLoginCreateButtonsVisibility(View.VISIBLE)
                createSubscriptionViews(View.INVISIBLE)
            } else {
                changeLoginCreateButtonsVisibility(View.INVISIBLE)
                createSubscriptionViews(View.VISIBLE)
            }

        })

        //clears the inputs in the edit text
        viewModel.clearEditText.observe(viewLifecycleOwner, Observer { clearNow ->
            if (clearNow) {
                clearEditTexts()
                viewModel.setClearEditToFalse()
            }
        })

        //responds when a valid user name and password is entered
        viewModel.validLoginRequest.observe(viewLifecycleOwner, Observer { requestResponse ->
            if (requestResponse) {
                logUserIn()
                viewModel.resetLoginState()
            }
        })

        return binding.root
    }

    private fun clearEditTexts() {
        binding.userNameEdit.setText("")
        binding.passwordEdit.setText("")
        binding.confirmPasswordEdit.setText("")
    }

    //sets visibilty to true or false
    private fun changeLoginCreateButtonsVisibility(visibility: Int) {
        binding.signUpButton?.visibility = visibility
        binding.loginButton?.visibility = visibility
    }

    //sets visibilty to true or false
    private fun createSubscriptionViews(visibility: Int) {
        binding.confirmPasswordEdit?.visibility = visibility
        binding.confirmPasswordText?.visibility = visibility
        binding.cancelButton?.visibility = visibility
        binding.createButton?.visibility = visibility
    }

    private fun logUserIn() {
        //Navigates to the wellcome page
        findNavController().navigate(Login_ScreenDirections.actionLoginScreenToWelcomeScreen())
       // resets the state of the Login variable to false
        viewModel.restoreLoginState()
    }

}