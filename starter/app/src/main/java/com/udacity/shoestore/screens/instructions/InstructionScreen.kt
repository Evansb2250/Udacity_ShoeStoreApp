package com.udacity.shoestore.screens.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionScreenBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



class InstructionScreen : Fragment() {

    private lateinit var binding: FragmentInstructionScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instruction__screen, container, false)


        binding.button.setOnClickListener{ navigateToShoeList() }

        return binding.root
    }

    private fun navigateToShoeList() {
        findNavController().navigate(InstructionScreenDirections.actionInstructionScreen2ToShoeListing())
    }


}


