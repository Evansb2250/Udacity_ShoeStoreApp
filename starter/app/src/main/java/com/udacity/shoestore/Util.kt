package com.udacity.shoestore

import android.app.Application
import android.os.Build
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.udacity.shoestore.dataStorage.Database
import com.udacity.shoestore.globalVariables.LoginEditTextVar
import com.udacity.shoestore.models.Shoe

class Util {
    companion object{


        // takes a list of shoe instances creates a list of txtView Instances and returns it
        fun createTextViewList(shoes: List<Shoe>, application: Application, newList: ArrayList<TextView>): ArrayList<TextView>{
            for(item in shoes){
                newList.add(addDataToTextView(TextView(application),item,application))
            }
            return newList
        }




        // customizes the textView using the Shoe instance
        private fun addDataToTextView(textView: TextView, shoe:Shoe,  application: Application) : TextView {
            textView.tag = shoe.uniqueId
            textView.setText(shoe.name)
            textView.setTextAppearance(application, R.style.shoeTextViewStyle)
            textView.layoutParams = createParameters()
            textView.typeface = ResourcesCompat.getFont(application,R.font.aclonica)

            return textView
        }

        // sets Text Content to Wrap_content
        private fun createParameters(): ViewGroup.LayoutParams {
            return ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }


    }
}


// Checks to see if the password matches
 fun doesPasswordsMatch(): Boolean = LoginEditTextVar.password.equals(LoginEditTextVar.passwordAuthentication)
//Checks to see if the user already exist before adding to the hashMap
 fun doesUserExist() :Boolean =  Database.userTable.containsUser(LoginEditTextVar.email)






