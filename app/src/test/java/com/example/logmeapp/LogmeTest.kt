package com.example.logmeapp

import org.junit.Test

import com.google.common.truth.Truth.assertThat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LogmeTest {

    fun getTestTimeStamp():String{
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:MM-")
        return current.format(formatter)
    }

    init {
        Logme.start(Logme.Environment.DEBUGGING, "test", "session", "user")
    }

    @Test
    fun start() {
    }

    @Test
    fun log() {
        Logme.log(Logme.Severity.NOTIFICATION, "test this log")
        assertThat(Logme.stest).isEqualTo("${getTestTimeStamp()} (NOTIFICATION) DEBUGGING [session] - test: test this log \n")
    }

    @Test
    fun emergency() {
        Logme.emergency("emergency message")
        assertThat(Logme.stest).isEqualTo("${getTestTimeStamp()} (EMERGENCY) DEBUGGING [session] - test: emergency message \n")
    }

    @Test
    fun alert() {
        Logme.alert("alert message")
        assertThat(Logme.stest).isEqualTo("${getTestTimeStamp()} (ALERT) DEBUGGING [session] - test: alert message \n")

    }

    @Test
    fun warn() {
        Logme.warn("warn message")
        assertThat(Logme.stest).isEqualTo("${getTestTimeStamp()} (WARNING) DEBUGGING [session] - test: warn message \n")
    }

    @Test
    fun error() {
        Logme.error("error message")
        assertThat(Logme.stest).isEqualTo("${getTestTimeStamp()} (ERROR) DEBUGGING [session] - test: error message \n")

    }

    @Test
    fun testNotify() {
        Logme.notify("notify message")
        assertThat(Logme.stest).isEqualTo("${getTestTimeStamp()} (NOTIFICATION) DEBUGGING [session] - test: notify message \n")

    }

    @Test
    fun critical() {
        Logme.critical("critical message")
        assertThat(Logme.stest).isEqualTo("${getTestTimeStamp()} (CRITICAL) DEBUGGING [session] - test: critical message \n")

    }

    @Test
    fun info() {
        Logme.info("info message")
        assertThat(Logme.stest).isEqualTo("${getTestTimeStamp()} (INFORMATIONAL) DEBUGGING [session] - test: info message \n")

    }

    @Test
    fun debug() {
        Logme.debug("debug message")
        assertThat(Logme.stest).isEqualTo("${getTestTimeStamp()} (DEBUGGING) DEBUGGING [session] - test: debug message \n")

    }
}