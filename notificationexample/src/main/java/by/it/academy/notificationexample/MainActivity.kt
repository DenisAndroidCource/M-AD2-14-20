package by.it.academy.notificationexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        createPendingNotification()
    }

    private fun createNotification() {
        val notification = NotificationCompat.Builder(applicationContext, "CHANNEL")
            .setContentTitle("NOTIFICATION!!!!!!!!!!")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.notify(1, notification)
//        notificationManager.cancel(1)
    }

    private fun createPendingNotification() {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 1, intent, 0)

        val notification = NotificationCompat.Builder(applicationContext, "CHANNEL")
            .setContentTitle("NOTIFICATION!!!!!!!!!!")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .build()
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.notify(1, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "CHANNEL_ID",
                "Default channel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "default channel description"
            }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}