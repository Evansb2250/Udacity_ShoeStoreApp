package com.udacity.shoestore.dataStorage

class UserData {
    companion object{
        //Stores all the users in the system
        //TODO Check how you can add an object as value
        private var users:HashMap<String, String> =  HashMap()
     //   private var userLoggedIn:?User= null
    }

    fun isValidLogRequest(email:String, password: String):Boolean =
        users.containsKey(email) && users.get(email).equals(password)


     fun containsUser(email:String): Boolean {
         return users.containsKey(email)
     }

     fun createUser(email:String, password: String){
        users.put(email,password)
    }
}