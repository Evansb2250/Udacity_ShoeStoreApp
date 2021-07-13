package com.udacity.shoestore.screens.shoeListing

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.Util
import com.udacity.shoestore.databinding.FragmentShoeListingBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.activity_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [ShoeListing.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeListing : Fragment() {

    private val args by navArgs<ShoeListingArgs>()
    private var screenOrientation: Int = -1
    private lateinit var binding: FragmentShoeListingBinding

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_listing, container, false)

        setUpFloatingActionButtons()
        getScreenOrientation()

//
//        if(args.shoe== null){
//            Toast.makeText(requireContext(), "NOTHING IN THE ARG", Toast.LENGTH_SHORT).show()
//        }else{
//            Toast.makeText(requireContext(), "Found SOmething good", Toast.LENGTH_SHORT).show()
//             args.
//        }
//


//
//       val textView  = Util.createTextView(Shoe("fll",2.0,"matrix","dsadsa"),requireActivity().application)
//
//
//        binding.linearLayout?.addView(textView)


      //  (activity as AppCompatActivity?)!!.supportActionBar?.show()
        setHasOptionsMenu(true)
        return binding.root


    }




    private fun setUpFloatingActionButtons() {

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(ShoeListingDirections.actionShoeListingToShoeForm())
        }

    }

    private fun getScreenOrientation() {
        val orientation = activity?.getResources()?.getConfiguration()?.orientation
        screenOrientation = if (orientation == 1) 1 else 2
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //    super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }




    companion object {
        private const val PORTRAIT = 1
    }

}