package com.training.victor.development.test

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.training.victor.development.R
import com.training.victor.development.assertions.RecyclerViewItemCountAssertion.Companion.withItemCount
import com.training.victor.development.ui.MainActivity
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.hamcrest.Matchers.greaterThan
import org.junit.Assert
import org.junit.Rule
import java.util.*

class FirstLaunchTest {
    @Rule val mainActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    private lateinit var mainActivity: MainActivity

    @Before
    fun setUp() {
        Intents.init()
        mainActivityTestRule.launchActivity(Intent())
        mainActivity = mainActivityTestRule.activity
    }

    @After
    fun tearDown() {
        Intents.release()
        mainActivity.finishAffinity()
    }


    // --------------------------------------------- TEST CASES ---------------------------------------------
    @Given("^a user launch the app for first time")
    fun a_user_launch_the_app_for_first_time() {
        Assert.assertNotNull(mainActivity)
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @When("profiles list is requested")
    fun profiles_list_is_requested() {
        onView(withId(R.id.lstPost)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Then("list is fulfilled")
    fun list_is_fulfilled() {
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.lstPost)).check(withItemCount(greaterThan(0)))
    }
}