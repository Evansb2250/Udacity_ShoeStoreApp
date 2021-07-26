package com.udacity.shoestore.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeScreenBinding



// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [Welcome_Screen.newInstance] factory method to
 * create an instance of this fragment.
 */
class Welcome_Screen : Fragment() {


    private lateinit var binding:FragmentWelcomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome__screen, container, false)

        // navigates to the instruction screen
        binding.imageButton.setOnClickListener{
        findNavController().navigate(Welcome_ScreenDirections.actionWelcomeScreenToInstructionScreen2())
        }



      return binding.root
    }


}