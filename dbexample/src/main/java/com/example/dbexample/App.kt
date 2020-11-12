package com.example.dbexample

import android.app.Application

class App : Application() {
    val dbHelper: DBHelper = DBHelper(this)
}