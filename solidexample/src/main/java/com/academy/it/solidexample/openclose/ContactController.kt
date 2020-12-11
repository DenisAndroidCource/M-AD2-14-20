package com.academy.it.solidexample.openclose

class ContactController {

    private val list = listOf<EmailValidator>(
        GmailValidator(),
        RamblerlValidator(),
        YandexValidator(),
        YahooValidator()
    )

    fun validateEmailAddress(email: String) {
        list.forEach { validator -> validator.controlEmail(email) }
//        if (email.endsWith("gmail.com", true)) {
//
//        } else if (email.endsWith("yandex.ru", true)) {
//
//        } else if (email.endsWith("rambler.ru", true)) {
//
//        } else if (email.endsWith("yahoo.com", true)) {
//
//        }
    }
}