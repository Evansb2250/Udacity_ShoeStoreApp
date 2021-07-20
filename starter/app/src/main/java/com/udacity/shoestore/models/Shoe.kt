package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.net.PasswordAuthentication


data class Shoe(var name: String, var size: Double, var company: String, var description: String,
                val images: List<String> = mutableListOf(), val uniqueId:Int)


