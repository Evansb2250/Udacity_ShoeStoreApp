package com.udacity.shoestore.screens.forms

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.shoestore.constants.COMPANY
import com.udacity.shoestore.constants.DESC
import com.udacity.shoestore.constants.NAME
import com.udacity.shoestore.constants.SIZE
import com.udacity.shoestore.createShoe
import com.udacity.shoestore.globalVariables.AddShoeEditTextVar
import com.udacity.shoestore.resetVariables
import com.udacity.shoestore.screens.shoeListing.ShoeListingViewModel
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ShoeFormViewModelTest {

    @Test
    fun testGetShoe_notNull_newShoe() {
        //Test adding data to the global variables used for 2-way databinding
        createShoe("sam", "nike", "4.5", "")
        //returns an object containing the data in the edit text
        val result = getShoeFormViewModel().getShoe()
        //checks if statement is null
        result?.let{
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
    fun getShoe_InsufficientShoeDetail_returnNull(){
        //entered a shoe in the editText with a missing shoe size
        createShoe("ShoeName", "ShoeCompany", "", "Shoe Discription")
        //call getShoe to see if the instance was created
        val result = getShoeFormViewModel().getShoe()
        //checks value
        assertEquals(null, result)
    }



    //returns an anonymous instance of ShoeListingViewModel
    private fun getShoeFormViewModel(): ShoeFormViewModel = ShoeFormViewModel()




}