package com.udacity.shoestore

import android.app.Application
import android.graphics.Color
import android.os.Build
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.udacity.shoestore.models.Shoe

class Util {
    companion object{

        @RequiresApi(Build.VERSION_CODES.M)
        fun createTextView(shoe: Shoe, application: Application): TextView{
            val textView = addDataToTextView(TextView(application),shoe,application)
            return textView
        }



        private fun addDataToTextView(textView: TextView, shoe:Shoe,  application: Application) : TextView {
            val typFace = ResourcesCompat.getFont(application,R.font.aclonica)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { textView.setTextAppearance(R.style.TextAppearance_AppCompat_Display1) }
            textView.setText(shoe.name)
            textView.setTextColor(Color.parseColor("#191515"))
            textView.setTextSize(35f)
            textView.layoutParams = createParameters()
            textView.typeface = typFace
            return textView
        }



        private fun createParameters(): ViewGroup.LayoutParams {
            var param = ViewGroup.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            return param
        }


    }
}


