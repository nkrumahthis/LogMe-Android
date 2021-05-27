package com.example.logmeapp

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.FileWriter

class Logme(
    private val context: Context,
    private val environment: String,
    private val tag: String,
    private val session: String
) {
    val severity = arrayOf(
        "EMERGENCY",
        "ALERT",
        "CRITICAL",
        "ERROR",
        "WARNING",
        "NOTIFICATION",
        "INFORMATIONAL",
        "DEBUGGING"
    )
    val environments =
        arrayOf("DEBUGGING", "STAGING", "PRODUCTION")

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
        val str = String.format(
            "(%d) %d [%d] -%d: %d", severity, environment, session, tag, message
        )
        println(str)
        uploadFile("logme.log", writeFileOnInternalStorage("logme.log", str))
    }

    private fun writeFileOnInternalStorage(
        filename: String,
        body: String
    ): File? {
        var file: File? = null
        val dir = File(context.applicationContext.filesDir, "logme")
        if (!dir.exists()) {
            dir.mkdir()
        }
        try {
            val logfile = File(dir, filename)
            val writer = FileWriter(logfile)
            writer.append(body)
            writer.flush()
            writer.close()
            file = logfile
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return file
    }

    private fun uploadFile(filename: String, logfile: File?) {
        val file = Uri.fromFile(logfile)
        val logRef = storageReference.child("logs/$filename")
        logRef.putFile(file)
            .addOnSuccessListener {
                Toast.makeText(
                    context,
                    "Log upload Successful",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Log upload error", Toast.LENGTH_SHORT).show()
            }
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
    }
}