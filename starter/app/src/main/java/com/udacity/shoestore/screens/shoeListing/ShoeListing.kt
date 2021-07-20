package com.udacity.shoestore.screens.shoeListing

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.Util
import com.udacity.shoestore.constants.LANDSCAPE
import com.udacity.shoestore.constants.PORTRAIT
import com.udacity.shoestore.databinding.FragmentShoeListingBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_shoe_listing.*
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [ShoeListing.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeListing : Fragment() {




    //Creates a shared ViewModel
    private val viewModel: ShoeListingViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListingBinding

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_listing, container, false)




        viewModel.shoeList.observe(viewLifecycleOwner, Observer { listOfShoes ->

            val listOfTextViews = Util.createTextViewList(
                listOfShoes.toList(),
                requireActivity().application,
                ArrayList<TextView>()
            )


            listOfTextViews.forEach { textView ->
                createOnClickTV(textView)
                binding.linearLayout.addView(textView)
            }

        })


        setUpFloatingActionButtons()
        setHasOptionsMenu(true)
        return binding.root
    }








    private fun setUpFloatingActionButtons() {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(ShoeListingDirections.actionShoeListingToShoeForm())
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //    super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }


    private fun createOnClickTV(textView: TextView) {
        textView.setOnClickListener {
            Toast.makeText(requireContext(), it.tag.toString(), Toast.LENGTH_SHORT).show()
        }
    }


}

