package com.udacity.shoestore.dataStorage.tables

class UserTable {
    //Contains a hashMap using the email as the key and password as the value
    private var users: HashMap<String, String> = HashMap()

    //Checks to see if the entered email and password matches with any result in the HashMap
    fun isValidLogRequest(email: String, password: String): Boolean =
        users.containsKey(email) && users.get(email).equals(password)

    //Checks the HashMap to see if the user exist
    fun containsUser(email: String): Boolean {
        return users.containsKey(email)
    }

    //Adds a username and password to the hashMap
    fun createUser(email: String, password: String) {
        users.put(email, password)
    }
}