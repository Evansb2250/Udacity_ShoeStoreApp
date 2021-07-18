package com.udacity.shoestore.screens.shoeListing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.Shoe
import java.lang.IllegalArgumentException

class ShoeListingViewModel() : ViewModel() {

    private var _shoeList = ArrayList<Shoe>()
    var shoeList: LiveData<MutableList<Shoe>>


    init {
        shoeList = MutableLiveData(_shoeList)
    }


    fun addToInventory(shoe:Shoe){
      //check to see if it is in the list
        _shoeList.add(shoe)
        shoeList = MutableLiveData(_shoeList)
    }

    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}






