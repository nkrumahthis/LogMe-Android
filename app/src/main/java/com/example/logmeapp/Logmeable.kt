package com.example.logmeapp

import android.content.Context

interface Logmeable {

    fun start(environment: Logme.Environment, tag:String, session:String, user:String)

    fun stop(context: Context)

    fun log(severity:Logme.Severity, message:String?)

    fun emergency(message: String?)

    fun alert(message: String?)

    fun warn(message: String?)

    fun notify(message: String?)

    fun error(message:String?)

    fun critical(message: String?)

    fun info(message: String?)

    fun debug(message: String?)

}