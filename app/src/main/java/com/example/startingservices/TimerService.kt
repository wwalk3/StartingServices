package com.example.startingservices

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import androidx.lifecycle.Lifecycle.Event.Companion.downTo

class TimerService : Service() {

    lateinit var timerHandler : Handler

    inner class TimerBinder : Binder() {
        fun startTimer() {
            runTimer()
        }

        fun setHandler(handler: Handler) {
            timerHandler = handler
        }
    }

    override fun onBind(intent: Intent): IBinder {
       return TimerBinder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        runTimer()

        return super.onStartCommand(intent, flags, startId)
    }

    fun runTimer() {
        TimerThread().start()
    }

//    override fun onDestroy() {
//
//    }

    inner class TimerThread () : Thread () {
        override fun run(startPoint: Int) {
            for (i in startPoint downTo(0) ) {

                if(::timerHandler.isInitialized) {
                    timerHandler.sendEmptyMessage(i)
                }

                Thread.sleep(100)

            }

        }
    }
}