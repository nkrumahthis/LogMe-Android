package com.example.logmeapp

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * enums
 * timestamp
 * staging
 * upload file upon app terminate
 * get uuid string for session
 * use appname when tag is not available
 * file name should be session id + userid
 */

object Logme : Logmeable{

    private lateinit var mEnvironment: Environment
    private var mTag: String = ""
    private var mSession: String = ""
    private var mUser: String = ""
    private var str:String = ""
    var stest: String = ""


    override fun start(environment: Environment, tag: String, session: String, user:String) {
        mEnvironment = environment
        mTag = tag
        mSession = session
        mUser = user
    }

     override fun stop(context: Context) {
        try {
            val filename = "$mSession-$mUser"

            // make file object and create file
            val file = makeFile(context, filename)

            // write log ot file
            file.appendText(str)

            //upload file
            uploadFile(file, filename)
        } catch (e:Exception){
             Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

    }

    @SuppressLint("NewApi")
    override fun log(severity: Severity, message: String?) {
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:MM-")
        val timestamp = current.format(formatter)

        val s = "$timestamp ($severity) $mEnvironment [$mSession] - $mTag: $message \n"
        str += s
        stest = s

        if(mEnvironment != Environment.PRODUCTION) println(s)
    }

    override fun emergency(message: String?) {
        log(Severity.EMERGENCY, message)
    }

    override fun alert(message: String?) {
        log(Severity.ALERT, message)
    }

    override fun warn(message: String?) {
        log(Severity.WARNING, message)
    }

    override fun error(message: String?) {
        log(Severity.ERROR, message)
    }

    override fun notify(message: String?) {
        log(Severity.NOTIFICATION, message)
    }

    override fun critical(message: String?) {
        log(Severity.CRITICAL, message)
    }

    override fun info(message: String?) {
        log(Severity.INFORMATIONAL, message)
    }

    override fun debug(message: String?) {
        log(Severity.DEBUGGING, message)
    }

    enum class Severity{
        INFORMATIONAL, NOTIFICATION, DEBUGGING, WARNING, ERROR, CRITICAL, ALERT, EMERGENCY
    }

    enum class Environment{
        DEBUGGING, STAGING, PRODUCTION
    }

    private fun makeFile(context:Context, filename:String): File{

        val dir = File(context.applicationContext.filesDir, "logme")

        if (!dir.exists()) {
            dir.mkdir()
        }

        val file = File(dir, filename)

        file.createNewFile()

        return file
    }

    private fun uploadFile(file:File, filename:String) {

        val uri = Uri.fromFile(file)
        val storageReference: StorageReference = FirebaseStorage.getInstance().reference
        val logRef = storageReference.child("logs/logme-${filename}")
        logRef.putFile(uri)
    }


}