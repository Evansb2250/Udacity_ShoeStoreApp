package com.udacity.shoestore

import android.app.Application
import android.view.View
import android.widget.TextView
import androidx.core.view.marginLeft
import com.udacity.shoestore.models.Shoe

class Util {
    companion object{

        fun createTextView(shoe: Shoe, application: Application): TextView{
            val textView : TextView = TextView(application)
            textView.setText(shoe.name)
            textView.setTextSize(35f)
            return textView
        }

    }
}


