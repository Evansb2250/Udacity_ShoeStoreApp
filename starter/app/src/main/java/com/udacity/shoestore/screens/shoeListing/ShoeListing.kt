package com.udacity.shoestore.screens.shoeListing

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.Util

import com.udacity.shoestore.databinding.FragmentShoeListingBinding
import com.udacity.shoestore.screens.login.Login_Screen
import com.udacity.shoestore.screens.login.Login_ScreenDirections
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
    private val viewModel:ShoeListingViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListingBinding

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_listing, container, false)

        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        //Updates the linearLayout when an item is added or removed from the list
        viewModel.shoeListLiveData.observe(viewLifecycleOwner, Observer { listOfShoes ->
         //calls util function to create and style a textview based on the names of the shoe
            val listOfTextViews = Util.createTextViewList(
                listOfShoes.toList(),
                requireActivity().application,
                ArrayList()
            )

            //Clear previous text Views from the linearLayout
            binding.linearLayout.removeAllViews()

            //adds each textview to the linear layout
            listOfTextViews.forEach { textView ->
                createOnClickTV(textView); binding.linearLayout.addView(
                textView
            )
            }
        })



        // removes a selected Text View from the linear Layout
        binding.removeButton?.setOnClickListener { viewModel.deleteItem() }

        setUpFloatingActionButtons()

        //indicates that the fragment will have an option menu
        setHasOptionsMenu(true)
        return binding.root
    }



    private fun setUpFloatingActionButtons() {
        // navigates to the fragment responsible for adding shoes
        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(ShoeListingDirections.actionShoeListingToShoeForm())
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //    super.onCreateOptionsMenu(menu, inflater)
        // adds the R.menu.menu to the fragment
        inflater.inflate(R.menu.menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logOutId -> {
                // if the logout option was selected the user will navigate to the Login Option
                navigateToLogin()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun createOnClickTV(textView: TextView) {
        //TextView listener
        textView.setOnClickListener {
            //When a textView is selected the tag of that textView is passed to the viewModel to display its contents.
            viewModel.displayItem(it.tag.toString().toInt())
        }
    }

    private fun navigateToLogin() {
        findNavController().navigate(ShoeListingDirections.actionShoeListingToLoginScreen())
    }
}

