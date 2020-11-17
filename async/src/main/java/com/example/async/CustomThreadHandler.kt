package com.example.async

import android.os.Handler
import android.os.HandlerThread

class CustomThreadHandler(name: String) : HandlerThread(name) {

    private lateinit var handler: Handler

    fun prepareLooper(){
        handler = Handler(looper)
    }

    fun postTask(runnable: Runnable) {
        handler.post(runnable)
    }
}