package com.udacity.shoestore

import android.app.Application
import android.graphics.Color
import android.os.Build
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.udacity.shoestore.dataStorage.User
import com.udacity.shoestore.dataStorage.UserData
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

            val typFace = ResourcesCompat.getFont(application,R.font.aclonica)
            textView.tag = shoe.uniqueId
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { textView.setTextAppearance(R.style.TextAppearance_AppCompat_Display1) }
            textView.setText(shoe.name)
            textView.setTextColor(Color.parseColor("#191515"))
            textView.setTextSize(13f)
            textView.layoutParams = createParameters()
            textView.typeface = typFace
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
 fun doesUserExist(db: UserData) :Boolean =  db.containsUser(User.email)






