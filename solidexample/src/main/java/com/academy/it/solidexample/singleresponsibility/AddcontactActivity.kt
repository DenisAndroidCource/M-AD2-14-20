package com.academy.it.solidexample.singleresponsibility

class AddcontactActivity {

    private val databaseController = DatabaseController()

    fun onStop() {
        val contact = Contact("", "", "")
        databaseController.saveContact(contact)
    }

}