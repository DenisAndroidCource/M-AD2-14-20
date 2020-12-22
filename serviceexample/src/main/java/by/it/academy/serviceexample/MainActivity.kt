package by.it.academy.serviceexample

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var serviceActions: ServiceActions? = null

    private val serviceConnection = object: ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            serviceActions = service as ServiceActions
            val data = serviceActions?.getData()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            serviceActions = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.startService).setOnClickListener {
            startService(Intent(this@MainActivity, ServiceExample::class.java))
        }

        findViewById<Button>(R.id.stopService).setOnClickListener {
            stopService(Intent(this@MainActivity, ServiceExample::class.java))
        }

        bindService(
                Intent(this, ServiceExample::class.java),
                serviceConnection,
                0
        )
    }
}