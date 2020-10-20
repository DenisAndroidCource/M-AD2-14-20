package com.example.kotlinexample

class MyClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            // Java: ICalculator calc = new CalculatorImpl()
            val calculator: ICalculator = CalculatorImpl()
            System.out.println("Sum= ${calculator.sum(4, 8)}")
            System.out.println("Div= ${calculator.div(16, 8)}")
            System.out.println("Div= ${calculator.div(16, 0)}")
            System.out.println("++++++++++++++++++++++++++++++++")

            val user=User(name="Igor",address = "13-12",age = 18)
            val user2=User(name="Igor",address = "13-12")


        }
    }
}
