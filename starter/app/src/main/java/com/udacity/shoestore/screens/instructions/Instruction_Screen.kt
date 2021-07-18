package com.udacity.shoestore.screens.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionScreenBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [Instruction_Screen.newInstance] factory method to
 * create an instance of this fragment.
 */
class Instruction_Screen : Fragment() {

    private lateinit var binding: FragmentInstructionScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
                  override fun handleOnBackPressed() {
                // overrides the on back pressed method to navigate back to the shoe list
                  navigateToShoeList()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instruction__screen, container, false)


        binding.button.setOnClickListener{
          navigateToShoeList()
        }



        return binding.root
    }

    private fun navigateToShoeList() {
        findNavController().navigate(Instruction_ScreenDirections.actionInstructionScreen2ToShoeListing())
    }


}


