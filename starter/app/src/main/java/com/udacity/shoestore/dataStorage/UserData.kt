package com.udacity.shoestore.dataStorage

class UserData {
    companion object{
        //Stores all the users in the system
        private var users:HashMap<String, String> =  HashMap<String, String>()
    }


     fun isValidLogRequest(email:String, password: String):Boolean =
         if(users.containsKey(email) && users.get(email).equals(password)) true else false



     fun containsUser(email:String): Boolean {
         return users.containsKey(email)
     }

     fun createUser(email:String, password: String){
        users.put(email,password)
    }
}