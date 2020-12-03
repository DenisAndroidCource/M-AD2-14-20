package com.example.networkexample.data

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLocationLifecycle: LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop(){

    }

}