package com.example.kotlinexample

class CalculatorImpl : ICalculator {
    override fun sum(a: Int, b: Int) = a + b

    override fun div(a: Int, b: Int) = if (b != 0) a / b else null
}