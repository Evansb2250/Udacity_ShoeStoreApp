package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())




//        this.setSupportActionBar(findViewById(R.id.toolbar))
//        this.supportActionBar?.setTitle(R.string.actionBarTitle)
//        this.supportActionBar?.show()


    }






}


