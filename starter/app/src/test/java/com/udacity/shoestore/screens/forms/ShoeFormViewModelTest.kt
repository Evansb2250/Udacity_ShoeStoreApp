package com.udacity.shoestore.screens.forms

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.shoestore.globalVariables.EditTextVar
import com.udacity.shoestore.screens.shoeListing.ShoeListingViewModel
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ShoeFormViewModelTest {
    private val NAME = 0
    private val COMPANY = 1
    private val SIZE = 2
    private val DESC = 3


    @Test
    fun testGetShoe_notNull_newShoe() {
        //Test adding data to the global variables used for 2-way databinding
        createShoe("sam", "nike", "4.5", "")
        //returns an object containing the data in the edit text
        val result = ShoeFormViewModel().getShoe()
        //checks if statement is null
        if (result != null) {
            //test results
            assertEquals("sam", result.name)
        }

    }


    @Test
    fun testGetShoe_Null_returnsNull() {
        resetVariables()
        // test an empty
        val result = ShoeFormViewModel().getShoe()
        assertEquals(null, result)

    }


    @Test
    fun testAddShoe_ShoeInst_sizeOne() {
        resetVariables()
        val result = addToList(listOf(listOf("same", "nike", "4.5", "")), getViewModel())
        assertEquals(1, result.getListSize())

    }


    @Test
    fun testRemoveShoe_RemoveOne_ZeroShoesLeft() {

        val data = listOf(listOf("same", "nike", "4.5", ""))
        val uniqueIDs2Remove = listOf(0)

        //Returns a viewModel with the data already inserted
        val originalList = addToList(data, getViewModel())

        //  removes any shoe with a unique ID of 0 and returns the results back
        val userModificationResults = selectAndDeleteShoes(uniqueIDs2Remove, originalList)

        //Checks if the output matches the expected results
        assertEquals(0, userModificationResults.getListSize())

    }


    @Test
    fun testRemovingShoes_TWO_OneShoeLeft() {
        resetVariables()
        // Data to enter in the list
        val data = listOf(
            listOf("same", "nike", "4.5", ""),
            listOf("airMax", "classics", "8.9", "new Balance"),
            listOf("Way Verse", "my own brand", "11.3", "white and gold")
        )

        //creates a viewModel with the shoe data inserted
        val originalList = addToList(data, getViewModel())

        //modifies the list by removing the shoe with id 0
        val userModificationResults = selectAndDeleteShoes(listOf(0), originalList)

        //Test the results
        assertEquals(2, userModificationResults.getListSize())

    }


    @Test
    fun testRemovingShoesAndAdding_TWO_OneShoeLeft() {
        resetVariables()
        // Data to enter in the list
        val data = listOf(
            listOf("same", "nike", "4.5", ""),
            listOf("airMax", "classics", "8.9", "new Balance"),
            listOf("Way Verse", "my own brand", "11.3", "white and gold")
        )
        //Unique Id's to be removed
        val uniqueIDs2Remove = listOf(0, 1)

        //creates a viewmodel with the data inserted
        val originalList = addToList(data, getViewModel())

        //Modifies the list by removing id 0 and 1
        var userModificationResults = selectAndDeleteShoes(uniqueIDs2Remove, originalList)

        //Re-inserts the data again into the modified list
        userModificationResults = addToList(data, userModificationResults)

        assertEquals(4, userModificationResults.getListSize())

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


    private fun addToList(
        data: List<List<String>>,
        list: ShoeListingViewModel
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
            list.addToInventory(ShoeFormViewModel().getShoe()!!)

            //Increments the global counter to keep track of the number of shoes created and assigns
            // the number to the shoe as a unique ID.
            ShoeFormViewModel().incrementBarCode()
        }

        //returns the list once all the data is inserted.
        return list
    }


    //returns an anonymous instance of ShoeListingViewModel
    private fun getViewModel(): ShoeListingViewModel = ShoeListingViewModel()


    //Resets static variables
    private fun resetVariables(){
        EditTextVar.clear()
        ShoeFormViewModel.resetBarCode()
    }


    //Function to added data to the global variables used for 2-way databinding
    //This information is used to create a new shoe.
    private fun createShoe(name: String, company: String, size: String, desc: String) {
        EditTextVar.shoeName = name
        EditTextVar.companyName = company
        EditTextVar.shoeDiscription = desc
        EditTextVar.shoeSizeString = size
    }
}