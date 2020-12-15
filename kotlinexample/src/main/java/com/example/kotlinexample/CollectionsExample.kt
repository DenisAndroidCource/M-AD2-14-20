package com.example.kotlinexample

inline fun foo123() : Boolean {
    return true
}

class CollectionsExample {

    val fooVal: (String) -> Int = { str -> str.length }
    val fooVal2: (String, String) -> Int = { str1, str2 ->
        if (str1.isNotEmpty()) {
            str1.length
        } else if (str2.isNotEmpty()){
            str2.length
        } else {
            str1.length + str2.length
        }
        90
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            foo123()

            val mutableList = mutableListOf<Int>(3, 7)
            mutableList.add(5)
            mutableList.add(5)
            mutableList.add(5)
            mutableList.add(5)
            mutableList.add(5)

            mutableList.filter { it % 2 == 0 }
                    .map { item -> item.toString() }
                    .toList()
            foo123()
            val unmutableList = listOf<Int>(4, 7)
//            unmutableList.add(435)
            foo123()
            val array: Array<Int> = arrayOf(0, 0)
        }
    }

    fun listFoo(): List<Int> {
        val list = mutableListOf(0 ,9)
        list.add(45345)

        funFoo(fooVal)

        return list
    }

   fun funFoo(param: (String) -> Int) {
       param("dfgfdsdfs")
   }
    fun funFoo2(param: Function1<String, Int>) {
       param("dfgfdsdfs")
   }
}