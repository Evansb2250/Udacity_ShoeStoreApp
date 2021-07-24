package com.udacity.shoestore.dataStorage

import com.udacity.shoestore.dataStorage.tables.UserTable

//Singleton class containing an instance of users in a table
object Database {
    val userTable = UserTable()
}





