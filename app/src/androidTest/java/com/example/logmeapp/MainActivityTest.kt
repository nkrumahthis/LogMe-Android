package com.example.logmeapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class MainActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun onInit(){
        onView(withId(R.id.tvDisplay))
            .check(matches(withText("0")))
    }

    @Test
    fun createCount(){
        // Start on home screen
        onView(withId(R.id.btCount)).perform(click())
        onView(withId(R.id.tvDisplay))
            .check(matches(withText("1")))

    }

    @Test
    fun count() {
    }

    @Test
    fun reset() {
    }

    @Test
    fun getCount() {
    }

    @Test
    fun setCount() {
    }

    @Test
    fun onCreate() {
    }

    @Test
    fun onStop() {
    }
}