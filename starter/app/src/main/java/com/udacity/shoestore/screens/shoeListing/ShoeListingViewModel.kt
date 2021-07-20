package com.udacity.shoestore.screens.shoeListing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.globalVariables.EditTextVar
import com.udacity.shoestore.models.Shoe
import java.lang.IllegalArgumentException

class ShoeListingViewModel() : ViewModel() {

    private var itemSelected = -1;


    private var _shoeList = ArrayList<Shoe>()
    var shoeList: LiveData<MutableList<Shoe>>


    init {
        shoeList = MutableLiveData(_shoeList)
    }


    fun addToInventory(shoe: Shoe) {
        //check to see if it is in the list
        _shoeList.add(shoe)
        shoeList = MutableLiveData(_shoeList)
    }

    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }


    fun displayItem(id: Int) {
        for (shoe in _shoeList) {
            if (shoe.uniqueId == id) {
                initializeVar(shoe)
            }
        }
    }




    private fun initializeVar(shoe: Shoe) {
        EditTextVar.shoeName = shoe.name
        EditTextVar.companyName = shoe.company
        EditTextVar.shoeSizeString = shoe.size.toString()
        EditTextVar.shoeDiscription = shoe.description

        //indicates the most recent item that is selected
        itemSelected = shoe.uniqueId
    }


}









