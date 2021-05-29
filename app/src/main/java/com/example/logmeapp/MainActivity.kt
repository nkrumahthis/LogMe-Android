package com.example.logmeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var count:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Logme.start(Logme.Environment.DEBUGGING, "Counter", "session", "user")

        tvDisplay.text = count.toString()

        btCount.setOnClickListener {
            count++
            Logme.info("count increased")
            updateDisplay()
        }

        btReset.setOnClickListener {
            count = 0
            updateDisplay()

        }

    }

    override fun onStop() {
        super.onStop()
        Logme.stop(this@MainActivity)
    }

    private fun updateDisplay(){
        tvDisplay.text = count.toString()
        Logme.info("display updated")
    }
}