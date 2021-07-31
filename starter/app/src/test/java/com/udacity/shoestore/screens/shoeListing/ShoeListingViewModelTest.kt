package com.udacity.shoestore.screens.shoeListing

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.shoestore.constants.COMPANY
import com.udacity.shoestore.constants.DESC
import com.udacity.shoestore.constants.NAME
import com.udacity.shoestore.constants.SIZE
import com.udacity.shoestore.createShoe
import com.udacity.shoestore.resetVariables
import com.udacity.shoestore.screens.forms.ShoeFormViewModel
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

//Need for testing with the viewModel
@RunWith(AndroidJUnit4::class)
class ShoeListingViewModelTest : TestCase(){
    //returns an anonymous instance of ShoeListingViewModel
    private fun getShoeListViewModel(): ShoeListingViewModel = ShoeListingViewModel()


    @Test
    fun testAddShoe_ShoeInst_sizeOne() {
        val result = createListOfShoeObjects(listOf(listOf("same", "nike", "4.5", "")), getShoeListViewModel())
        Assert.assertEquals(1, result.getListSize())

    }


    @Test
    fun testRemoveShoe_RemoveOne_ZeroShoesLeft() {

        val data = listOf(listOf("same", "nike", "4.5", ""))
        val uniqueIDs2Remove = listOf(0)

        //Returns a viewModel with the data already inserted
        val originalList = createListOfShoeObjects(data, getShoeListViewModel())

        //  removes any shoe with a unique ID of 0 and returns the results back
        val userModificationResults = selectAndDeleteShoes(uniqueIDs2Remove, originalList)

        //Checks if the output matches the expected results
        Assert.assertEquals(0, userModificationResults.getListSize())

    }


    @Test
    fun testRemovingShoes_TWO_OneShoeLeft() {
        // Data to enter in the list
        val data = listOf(
            listOf("same", "nike", "4.5", ""),
            listOf("airMax", "classics", "8.9", "new Balance"),
            listOf("Way Verse", "my own brand", "11.3", "white and gold")
        )

        //creates a viewModel with the shoe data inserted
        val originalList = createListOfShoeObjects(data, getShoeListViewModel())

        //modifies the list by removing the shoe with id 0
        val userModificationResults = selectAndDeleteShoes(listOf(0), originalList)

        //Test the results
        Assert.assertEquals(2, userModificationResults.getListSize())

    }


    @Test
    fun testRemovingShoesAndAdding_TWO_OneShoeLeft() {

        // Data to enter in the list
        val data = listOf(
            listOf("same", "nike", "4.5", ""),
            listOf("airMax", "classics", "8.9", "new Balance"),
            listOf("Way Verse", "my own brand", "11.3", "white and gold")
        )
        //Unique Id's to be removed
        val uniqueIDs2Remove = listOf(0, 1)

        //creates a viewmodel with the data inserted
        val originalList = createListOfShoeObjects(data, getShoeListViewModel())

        //Modifies the list by removing id 0 and 1
        var userModificationResults = selectAndDeleteShoes(uniqueIDs2Remove, originalList)

        //Re-inserts the data again into the modified list
        userModificationResults = createListOfShoeObjects(data, userModificationResults)

        Assert.assertEquals(4, userModificationResults.getListSize())

    }



    private fun createListOfShoeObjects(
        data: List<List<String>>,
        shoeObjectList: ShoeListingViewModel
    ): ShoeListingViewModel {

        resetVariables()
        for (row in 0..data.size - 1) {
            //creates new entries in the variables connected to the GUI by 2-way databinding
            createShoe(
                data.get(row).get(NAME),
                data.get(row).get(COMPANY),
                data.get(row).get(SIZE),
                data.get(row).get(DESC)
            )

            //adds each shoe object into the ShoeListing ViewModel arrayList that is wrapped by mutable and live data
            shoeObjectList.addToInventory(ShoeFormViewModel().getShoe()!!)

            //Increments the global counter to keep track of the number of shoes created and assigns
            // the number to the shoe as a unique ID.
            ShoeFormViewModel().incrementBarCode()
        }

        //returns the list once all the data is inserted.
        return shoeObjectList
    }



    private fun selectAndDeleteShoes(
        listOfID: List<Int>,
        viewModel: ShoeListingViewModel
    ): ShoeListingViewModel {
        //Iterates through a list containing the Unique ID's to remove from the viewModel
        for (index in 0..listOfID.size - 1) {
            //Simulates when a item is selected by clicking on its textview
            // the program then gets the tag of the view which is the same as the Unique ID
            viewModel.displayItem(listOfID.get(index))

            //  removes the item that is currently selected in the list
            viewModel.deleteItem()
        }
        //returns the viewModel after removing the specified shoes
        return viewModel
    }


}