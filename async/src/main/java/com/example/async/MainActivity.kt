package com.example.async

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.*
import java.util.function.Consumer
import java.util.function.Supplier

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var text: TextView
    private lateinit var handler: Handler

    private val executor = Executors.newFixedThreadPool(5)

    private val customHandlerThread = CustomThreadHandler("name").apply {
        prepareLooper()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        executor.submit(Runnable {
            for (i in 0..100) {
                Thread.sleep(100)
                Log.d("THREAD", Thread.currentThread().name)

                handler.post(Runnable {
                    progressBar.progress = i
                    text.text = i.toString()
                })
            }
        })

        val future: Future<Int> = executor.submit(Callable {
            Thread.sleep(100)
            100
        })

        val result = future.get()

        CompletableFuture.supplyAsync(Supplier {
            Thread.sleep(100)
            100
        }).thenApplyAsync {result -> result+1  }
                .thenRunAsync(Runnable {  }, mainExecutor)

        Observable.create<Int> {emitter -> emitter.onNext(100) }
                .map { data -> data.toString() }
                .map { strin -> strin.length }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {result -> text.text = result.toString()},
                        {throwable -> Log.e("ERROR", throwable.toString())}
                )

        handler = Handler(mainLooper)
        setContentView(R.layout.activity_main)
        text = findViewById<TextView>(R.id.text)
        progressBar = findViewById<ProgressBar>(R.id.progress).apply {
            max = 100
        }
        doJob()

        customHandlerThread.postTask(Runnable {
            for (i in 0..100) {
                Thread.sleep(100)
                Log.d("THREAD", Thread.currentThread().name)

                handler.post(Runnable {
                    progressBar.progress = i
                    text.text = i.toString()
                })
            }
        })

        customHandlerThread.postTask(Runnable {
            for (i in 0..100) {
                Thread.sleep(100)
                Log.d("THREAD", Thread.currentThread().name)

                handler.post(Runnable {
                    progressBar.progress = i
                    text.text = i.toString()
                })
            }
        })

        customHandlerThread.postTask(Runnable {
            for (i in 0..100) {
                Thread.sleep(100)
                Log.d("THREAD", Thread.currentThread().name)

                handler.post(Runnable {
                    progressBar.progress = i
                    text.text = i.toString()
                })
            }
        })
        customHandlerThread.interrupt()


    }

    private fun doJob() {
        Thread {
            for (i in 0..100) {
                Thread.sleep(100)
                Log.d("THREAD", Thread.currentThread().name)

                handler.post(Runnable {
                    progressBar.progress = i
                    text.text = i.toString()
                })
            }
        }.start()
    }
}