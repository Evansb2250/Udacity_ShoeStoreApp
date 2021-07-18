package com.udacity.shoestore

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.screens.instructions.Instruction_Screen
import com.udacity.shoestore.screens.login.Login_ScreenDirections
import com.udacity.shoestore.screens.shoeListing.ShoeListingDirections
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())




        this.setSupportActionBar(findViewById(R.id.toolbar))
        this.supportActionBar?.setTitle(R.string.actionBarTitle)
        this.supportActionBar?.show()


    }






}


