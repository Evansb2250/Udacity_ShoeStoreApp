package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Debug library to make it easier to find issues in code
        Timber.plant(Timber.DebugTree())



            //sets up the actionBar since the default action bar is not being used
          this.setSupportActionBar(findViewById(R.id.toolbar))
           //Sets title to a string value which is empty
          this.supportActionBar?.setTitle(R.string.actionBarTitle)
          //shows the action bar at the start of the application.
          this.supportActionBar?.show()


    }






}


