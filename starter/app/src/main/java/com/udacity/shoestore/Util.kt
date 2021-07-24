package com.udacity.shoestore

import android.app.Application
import android.graphics.Color
import android.os.Build
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.udacity.shoestore.dataStorage.Database
import com.udacity.shoestore.dataStorage.User
import com.udacity.shoestore.models.Shoe

class Util {
    companion object{


        @RequiresApi(Build.VERSION_CODES.M)
        fun createTextViewList(shoes: List<Shoe>, application: Application, newList: ArrayList<TextView>): ArrayList<TextView>{
            for(item in shoes){
                newList.add(addDataToTextView(TextView(application),item,application))
            }
            return newList
        }





        private fun addDataToTextView(textView: TextView, shoe:Shoe,  application: Application) : TextView {
            textView.tag = shoe.uniqueId
            textView.setText(shoe.name)
            textView.setTextAppearance(application, R.style.shoeTextViewStyle)
            textView.layoutParams = createParameters()
            textView.typeface = ResourcesCompat.getFont(application,R.font.aclonica)

            return textView
        }


        private fun createParameters(): ViewGroup.LayoutParams {
            return ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }


    }
}



 fun doesPasswordsMatch(): Boolean = User.password.equals(User.passwordAuthentication)
 fun doesUserExist() :Boolean =  Database.userTable.containsUser(User.email)






