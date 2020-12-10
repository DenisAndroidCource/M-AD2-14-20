package by.it.academy.solidexample.singleresponsibility

class AddcontactActivity {

    private val databaseController = DatabaseController()

    fun onStop() {
        val contact = Contact("", "", "")
        databaseController.saveContact(contact)
    }

}