package com.example.logmeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var logger:Logme
    private var count:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay.text = count.toString()
        btCount.setOnClickListener(View.OnClickListener {
            count++;
            tvDisplay.text = count.toString()
        })

        logger = Logme(this@MainActivity, "DEBUGGING", "Counter", "nkrumahthis");

    }
}