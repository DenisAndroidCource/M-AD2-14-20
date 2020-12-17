package by.it.academy.customactionbroadcast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.sendAction).setOnClickListener {
            val intent = Intent().apply {
                action = "by.it.academy.broadcastreceiverexample.CUSTOMACTION"
            }
            sendBroadcast(intent)
        }
    }
}