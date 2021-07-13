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


    private var screenOrientation: Int = -1

    //Creates a shared ViewModel
    private val  viewModel: ShoeListingViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListingBinding

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_listing, container, false)




        viewModel.shoeList.observe(viewLifecycleOwner, Observer { listOfShoes ->
            listOfShoes.forEach {
                val textView = Util.createTextView(it, requireActivity().application)
                createOnClickTV(textView)
                binding.linearLayout.addView(textView)
            }
        })


        setUpFloatingActionButtons()
        getScreenOrientation()



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
        screenOrientation = if (orientation == PORTRAIT) PORTRAIT else LANDSCAPE
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //    super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }


    private fun createOnClickTV(textView: TextView){
        textView.setOnClickListener{

            Toast.makeText(requireContext(),it.tag.toString(), Toast.LENGTH_SHORT).show()
        }
    }



    companion object {
        private const val PORTRAIT = 1
        private const val LANDSCAPE = 2
    }

}