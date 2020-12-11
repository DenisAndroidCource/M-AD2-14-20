package com.academy.it.solidexample.dependencyinversion

class ContactController {

    private val list = listOf<EmailValidator>(
        GmailValidator(),
        RamblerlValidator(),
        YandexValidator(),
        YahooValidator()
    )

    fun validateEmailAddress(email: String) {
        list.forEach { validator -> validator.controlEmail(email) }
    }
}