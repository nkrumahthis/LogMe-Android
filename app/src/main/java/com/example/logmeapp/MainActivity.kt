package com.example.logmeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var logger:Logme
    private var count:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay.text = count.toString()

        btCount.setOnClickListener {
            count++
            logger.info("count increased")
            updateDisplay()
        }

        btReset.setOnClickListener {
            count = 0
            updateDisplay()

        }

        logger = Logme(this@MainActivity, "DEBUGGING", "Counter", "nkrumahthis")

    }

    private fun updateDisplay(){
        tvDisplay.text = count.toString()
        logger.info("display updated")
    }
}