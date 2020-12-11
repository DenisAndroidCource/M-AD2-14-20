package com.academy.it.solidexample.dependencyinversion

open class Contact(
    val name: String,
    val info: String,
    val infoType: InfoType
) {
    fun foo(){}
}

class FullContact : Contact("", "", InfoType.SKYPE) {

}

enum class InfoType {
    PHONE, EMAIL, SKYPE
}