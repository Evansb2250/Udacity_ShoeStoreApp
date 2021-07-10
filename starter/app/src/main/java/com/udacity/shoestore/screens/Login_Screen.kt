package com.udacity.shoestore.screens

import android.content.res.Configuration
import android.database.DatabaseUtils
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginScreenFragmentBinding
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.text.ParsePosition

class Login_Screen : Fragment() {


    private lateinit var binding: LoginScreenFragmentBinding
    private lateinit var viewModel: LoginScreenViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.login__screen_fragment, container, false)
        viewModel = ViewModelProvider(this).get(LoginScreenViewModel::class.java)

        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)


        //sets onClick listeners for the buttons
        intializeButtons()


        viewModel.guiMessage.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })



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



        viewModel.validLoginRequest.observe(viewLifecycleOwner, Observer { requestResponse ->
            if(requestResponse) {
                logUserIn()
                viewModel.resetLoginState()
            }
        })




        return binding.root
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
                binding.signUpButton ->{
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