package com.example.logmeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    private lateinit var logger:Logme

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logger = Logme(this@MainActivity, "DEBUGGING", "Counter", "nkrumahthis");

    }
}