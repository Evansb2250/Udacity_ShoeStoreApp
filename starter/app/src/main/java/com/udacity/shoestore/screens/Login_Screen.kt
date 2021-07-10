package com.udacity.shoestore.screens

import android.database.DatabaseUtils
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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


// TODO create a map to hold accounts
// TODO create a viewModel that will hold the data entered
// TODO attach Login_screen to a viewModel
// TODO setup live data

class Login_Screen : Fragment() {



    private val viewModel: LoginScreenViewModel by activityViewModels<LoginScreenViewModel>()
    private lateinit var binding: LoginScreenFragmentBinding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.login__screen_fragment, container, false)

      //  viewModel = ViewModelProvider(this).get(LoginScreenViewModel::class.java)

        binding.loginScreenViewModel = viewModel

        binding.setLifecycleOwner(this)



           viewModel.loginState.observe(viewLifecycleOwner, Observer { isCreatingNewAccount ->
               if(isCreatingNewAccount) {
                   changeLoginCreateButtonsVisibility(View.INVISIBLE)
                   createSubscriptionViews(View.VISIBLE)
               }else{
                   changeLoginCreateButtonsVisibility(View.VISIBLE)
                   createSubscriptionViews(View.INVISIBLE)
                  }
                })







        binding.loginButton.setOnClickListener{
           findNavController().navigate(Login_ScreenDirections.actionLoginScreenToWelcomeScreen())
        }




        return binding.root
    }




    fun changeLoginCreateButtonsVisibility(visibility: Int){
           binding.signUpButton?.visibility = visibility
           binding.loginButton?.visibility = visibility
       }

    fun createSubscriptionViews(visibility: Int){
        binding.confirmPasswordEdit?.visibility = visibility
        binding.confirmPasswordText?.visibility = visibility
        binding.cancelButton?.visibility = visibility
        binding.createButton?.visibility = visibility
    }





}