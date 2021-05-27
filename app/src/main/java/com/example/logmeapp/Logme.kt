package com.example.logmeapp

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.FileWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Logme(
    private val context: Context,
    private val environment: String,
    private val tag: String,
    private val session: String
) {
    val EMERGENCY = "EMERGENCY"
    val ALERT = "ALERT"
    val CRITICAL = "CRITICAL"
    val ERROR = "ERROR"
    val WARNING = "WARNING"
    val NOTIFICATION = "NOTIFICATION"
    val INFORMATIONAL = "INFORMATIONAL"
    val DEBUGGING = "DEBUGGING"
    val STAGING = "STAGING"
    val PRODUCTION = "PRODUCTION"

    private val storageReference: StorageReference

    fun log(severity: String?, message: String?) {
        val str = "($severity) $environment [$session] - $tag: $message"
        println(str)
        writeLogToFile(str)
        uploadFile()
    }

    @SuppressLint("NewApi")
    private fun getFileName():String{
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("-yyyy-MM-dd")
        val formatted = current.format(formatter)

        return "logme-$formatted.log"
    }

    private fun getFile():File{

        val dir = File(context.applicationContext.filesDir, "logme")
        if (!dir.exists()) {
            dir.mkdir()
        }

        return File(dir, getFileName())
    }

    private fun createFileOnInternalStorage(){

        val file = getFile()

        // create a new file
        try {
            val isNewFileCreated: Boolean = file.createNewFile()

            if (isNewFileCreated) {
                println("file created successfully")
            } else {
                println("unable to create file")
            }
        } catch (e:Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

    }

    private fun writeLogToFile(text:String){
        getFile().appendText(text)
    }

    private fun uploadFile() {

        val file = Uri.fromFile(getFile())
        val logRef = storageReference.child("logs/${getFileName()}")
        logRef.putFile(file)
    }

    fun emergency(message: String?) {
        log(EMERGENCY, message)
    }

    fun alert(message: String?) {
        log(ALERT, message)
    }

    fun warn(message: String?) {
        log(WARNING, message)
    }

    fun nofify(message: String?) {
        log(NOTIFICATION, message)
    }

    fun info(message: String?) {
        log(INFORMATIONAL, message)
    }

    fun debug(message: String?) {
        log(DEBUGGING, message)
    }

    init {
        storageReference = FirebaseStorage.getInstance().reference
        createFileOnInternalStorage()
    }
}