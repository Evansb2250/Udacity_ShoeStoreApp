package com.udacity.shoestore.dataStorage

import com.udacity.shoestore.models.Shoe

class ShoeStorage {
    companion object{
        //Stores all the users in the system
        private var users:HashMap<String, Shoe> =  HashMap<String, Shoe>()
    }


}