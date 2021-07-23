package com.udacity.shoestore.screens.shoeListing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListingViewModel : ViewModel() {

    private var itemSelected = -1

    private var _currentShoe = MutableLiveData<Shoe>()
    var currentShoe: LiveData<Shoe> = _currentShoe
        set(value) {
            field = _currentShoe
        }




    private val shoeList = ArrayList<Shoe>()
    private val _shoeListLiveData = MutableLiveData<List<Shoe>>()
    var shoeListLiveData: LiveData<List<Shoe>> get()= _shoeListLiveData


    init {
        shoeListLiveData = MutableLiveData(shoeList)
    }

    fun addToInventory(shoe: Shoe) {
        //check to see if it is in the list
        shoeList.add(shoe)
        updateShoeList()
    }

    fun deleteItem(){
        var index = 0
        while(shoeList != null && index < shoeList.size ){
            if(shoeList.get(index).uniqueId == itemSelected){
                  deletIndex(index)
                break
            }
            index += 1
        }
    }


    fun displayItem(id: Int) {
        for ( shoe in shoeList) {
            if (shoe.uniqueId == id) {
                itemSelected = shoe.uniqueId
                initializeVar(shoe)
            }

        }
    }


    private fun initializeVar(shoe: Shoe) {
        _currentShoe.value = shoe
        //indicates the most recent item that is selected
        itemSelected = shoe.uniqueId
    }



  //Test Function
    fun getListSize():Int{
        return _shoeListLiveData.value?.size ?:-1
    }



   private fun deletIndex(index:Int){
       shoeList.removeAt(index)
       itemSelected = -1
       _currentShoe.value = clearShoeObject()
       updateShoeList()
    }




    private fun updateShoeList() {
        _shoeListLiveData.value  = shoeList
    }




    private fun clearShoeObject():Shoe{
        return Shoe("",0.0,"","",uniqueId = -1,shoeSizeString = "")
    }

}











