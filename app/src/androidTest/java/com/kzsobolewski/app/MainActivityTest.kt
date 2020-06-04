package com.kzsobolewski.app

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kzsobolewski.mygarden.main.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

fun checkIfVisible(resource: Int): ViewInteraction =
    getRes(resource).check(matches(isDisplayed()))

fun getRes(resource: Int): ViewInteraction = onView(withId(resource))

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get: Rule
    val activityScenario = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testPlantsFragment() {
        checkIfVisible(R.id.fragment_plants)
    }

    @Test
    fun testNewPlantFragment() {
        checkIfVisible(R.id.new_plant_fab).perform(click())
        checkIfVisible(R.id.fragment_new_plant)
        pressBack()
    }

    @Test
    fun testSearchFragment() {
        getRes(R.id.main).perform(swipeLeft())
        checkIfVisible(R.id.fragment_search)
    }
}