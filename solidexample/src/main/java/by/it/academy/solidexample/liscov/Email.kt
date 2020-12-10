package by.it.academy.solidexample.liscov

class RamblerEmail: Email(true)  {
}

class YandexEmail: Email(false)  {
}

class GmailEmail: Email(true) {
}

open class Email(val isPrivate: Boolean)

class EmailController {
    fun validate(email: Email) {
        if (email.isPrivate) {
            throw IllegalAccessError()
        }
    }
}