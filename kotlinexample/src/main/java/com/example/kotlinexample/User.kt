package com.example.kotlinexample

class User(
        val name: String,
        var address: String,
        private val age: Int = 18
) {
    private var phone: String
    private lateinit var email: String

    // !!
    init {
        phone = "+37529-222-22-22"
    }

    constructor() : this("Vova", "13-12", 22)

    fun isAdult() = age == 18

}