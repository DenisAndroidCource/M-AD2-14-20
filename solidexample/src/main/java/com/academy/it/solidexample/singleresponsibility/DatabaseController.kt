package com.academy.it.solidexample.singleresponsibility

class DatabaseController {

    private val database = Database()

    fun saveContact(contact: Contact){
        database.execSQL("insert into $contact")
    }

}