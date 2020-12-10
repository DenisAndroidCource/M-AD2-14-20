package by.it.academy.solidexample.openclose

interface EmailValidator {
    fun controlEmail(email: String) : Boolean
}

class GmailValidator : EmailValidator {
    override fun controlEmail(email: String): Boolean {
        TODO("Not yet implemented")
    }
}

class RamblerlValidator : EmailValidator {
    override fun controlEmail(email: String): Boolean {
        TODO("Not yet implemented")
    }
}

class YandexValidator : EmailValidator {
    override fun controlEmail(email: String): Boolean {
        TODO("Not yet implemented")
    }
}

class YahooValidator : EmailValidator {
    override fun controlEmail(email: String): Boolean {
        TODO("Not yet implemented")
    }
}