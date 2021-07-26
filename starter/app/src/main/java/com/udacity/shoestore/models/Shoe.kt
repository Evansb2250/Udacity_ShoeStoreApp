package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.net.PasswordAuthentication

//Data class for shoe
// The shoesizeString variable is there so an object of Shoe can be used for 2-way binding.
// The UniqueID  variable is used to assign an id to that specific instance.
// That id value will be used to help identify which shoe instance was selected
// when clicking on a textview by using id to set the value for the TextView Tag
data class Shoe(var name: String, var size: Double = 0.0,
                var company: String, var description: String,
                val uniqueId:Int, val shoeSizeString : String)


