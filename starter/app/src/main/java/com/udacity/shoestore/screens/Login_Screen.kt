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




        viewModel.loginState.observe(viewLifecycleOwner, Observer { isCreatingNewAccount ->
            if (isCreatingNewAccount) {
                changeLoginCreateButtonsVisibility(View.INVISIBLE)
                createSubscriptionViews(View.VISIBLE)
            } else {
                changeLoginCreateButtonsVisibility(View.VISIBLE)
                createSubscriptionViews(View.INVISIBLE)
                viewModel.clearVariableData()
            }
        })



        viewModel.validLoginRequest.observe(viewLifecycleOwner, Observer { accessPermission ->
            if (accessPermission) {
                findNavController().navigate(Login_ScreenDirections.actionLoginScreenToWelcomeScreen())
            }
        })


        viewModel.createButtonPressed.observe(viewLifecycleOwner, Observer { buttonPressed ->
            getDataFromGUI()
        })

        viewModel.logginButtonPressed.observe(viewLifecycleOwner, Observer { ifPressed ->
            if(ifPressed){
                getDataFromGUI()
                viewModel.resetButtonState(false)
            }
        })




        return binding.root
    }



    private fun getDataFromGUI(){
        val name = binding.userNameEdit.getText().toString()?:""
        val password = binding.passwordEdit.getText().toString()?:""
        viewModel.getUIDetails(name, password)
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


}