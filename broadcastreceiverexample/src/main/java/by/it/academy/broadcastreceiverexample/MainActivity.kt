package by.it.academy.broadcastreceiverexample

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val broadcastReceiver by lazy { TimeEventBroadcastReceiver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.sendAction).setOnClickListener {
            val intent = Intent().apply {
                action = CUSTOMACTION
                putExtra("KEY", "sadasda")
            }
            sendBroadcast(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
            addAction(CUSTOMACTION)
        }

        registerReceiver(broadcastReceiver, intentFilter)
    }

    companion object {
        private val CUSTOMACTION = "by.it.academy.broadcastreceiverexample.CUSTOMACTION"
    }
}