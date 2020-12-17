package by.it.academy.serviceexample

import android.app.Service
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import java.util.concurrent.TimeUnit

class ServiceExample : Service() {

    private fun createNotification() {
        val notification = NotificationCompat.Builder(applicationContext, "CHANNEL")
            .setContentTitle("Service is running")
            .build()
        startForeground(10, notification)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotification()
        Log.d(LOG, "onStartCommand startId: $startId")
        someJob(startId)
        return START_NOT_STICKY
    }

    private fun someJob(startId: Int) {
        val runnable = Runnable {
            Log.d(LOG, "someJob started: $startId")
            TimeUnit.SECONDS.sleep(5)
            Log.d(LOG, "someJob sleep: $startId")
            stopSelf(4)
            Log.d(LOG, "someJob stopSelf: $startId")
        }
        Thread(runnable).start()
    }

    override fun onBind(intent: Intent?): Nothing? = null

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG, "onDestroy")
    }

    companion object {
        private val LOG = "ServiceExample"
    }
}