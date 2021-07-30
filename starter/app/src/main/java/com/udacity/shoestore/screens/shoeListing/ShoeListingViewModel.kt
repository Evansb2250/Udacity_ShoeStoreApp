package com.udacity.shoestore.screens.shoeListing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListingViewModel : ViewModel() {

    private var itemSelected = -1

    private var _currentShoe = MutableLiveData<Shoe>()
    val currentShoe: LiveData<Shoe> get()= _currentShoe


    // wraps the mutable and Live data with the ArrayList to update the structures on changes
    // to the list
    private val shoeList = ArrayList<Shoe>()


    //Mutable Live data to that
    private val _shoeListLiveData = MutableLiveData<List<Shoe>>()
    var shoeListLiveData: LiveData<List<Shoe>> get() = _shoeListLiveData


    //public function that takes an instance of shoe and adds it the shoeList ArrayList
    fun addToInventory(shoe: Shoe) {
        // Adds a shoe to the list
        shoeList.add(shoe)
        //updates the mutableLiveData and LiveData var with the shoeList array value
        updateShoeList()
    }

    fun deleteItem() {
        shoeList.let {
            findAndDeleteItem()
        }
    }



    private fun findAndDeleteItem() {
        //variable to track indexes in the shoeList
        var index = 0
        while (index < shoeList.size) {
            if (shoeList.get(index).uniqueId == itemSelected) {
                deletIndex(index)
                break
            }
            //increments the index
            index += 1
        }
    }

    //the tag value for a textview is passed to the method when clicked on
    fun displayItem(id: Int) {
        //Checks to see if any shoe instances contain the same value as the id variable
        for (shoe in shoeList) {
            if (shoe.uniqueId == id) {
                //updates the itemSelected variable to indicate the unique id that was selected
                itemSelected = shoe.uniqueId
                //initializes the _currentShoe Var with the matching shoe instance.
                initializeVar(shoe)
            }

        }
    }

    //initializes the current var with a shoe selected in the GUI
    private fun initializeVar(shoe: Shoe) {
        _currentShoe.value = shoe
        //indicates the most recent item that is selected
        itemSelected = shoe.uniqueId
    }


    //Used to check if items are being removed and added for testing
    fun getListSize(): Int {
        return _shoeListLiveData.value?.size ?: -1
    }


    //Once index of item is found it is remove from list and GUI
    private fun deletIndex(index: Int) {
        shoeList.removeAt(index)
        clearValueForCurrentShoe()
        updateShoeList()
    }

    //resets the value to unselected state and removes data appearing in the GUI for shoe details
    private fun clearValueForCurrentShoe() {
        itemSelected = -1
        _currentShoe.value = clearShoeObject()
    }


    //Updates the mutableliveData for shoe list which triggers an event in the liveData variable.
    private fun updateShoeList() {
        _shoeListLiveData.value = shoeList
    }


    //Create an empty instance for the shoe class
    private fun clearShoeObject(): Shoe {
        return Shoe("", 0.0, "", "", uniqueId = -1, shoeSizeString = "")
    }


    init {
        //Uses the arraylist to initialize the mutablLiveData list.
        shoeListLiveData = MutableLiveData(shoeList)
    }


}











