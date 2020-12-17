package by.it.academy.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class TimeEventBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action ?: ""
        when (action) {
            Intent.ACTION_TIMEZONE_CHANGED -> print("TIME ZONE changes")
            Intent.ACTION_TIME_CHANGED -> print("TIME changed")
            else -> print("CUSTOMACTION")
        }
    }

    private fun print(msg: String) {
        Log.d("TimeEventBroadcast", "onReceive $msg")
        Log.d("TimeEventBroadcast", "Thread name ${Thread.currentThread().name}")
    }
}