package com.onefootball.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.onefootball.R
import com.onefootball.ui.settings.SettingsActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyNewsActivityTest {

    @get:Rule
    val mActivityRule = ActivityTestRule(MyNewsActivity::class.java)

    @Before
    fun beforeActivityLaunched() {
        Intents.init()
    }

    @After
    fun afterActivityFinished() {
        Intents.release()
    }

    /**Check that recyclerView is visible*/
    @Test
    fun isMainActivityVisible_onAppLaunch() {
        onView(withId(R.id.newsRecyclerView)).check(matches(isDisplayed()))
    }

    /**Test that the settings menu button opens the [SettingsActivity]*/
    @Test
    fun clickSettingsButton_OpensSettingsActivity() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText("Settings")).perform(click())
        intended(hasComponent(SettingsActivity::class.java.name))
    }

    /**Test that the settings menu button opens the [SettingsActivity] and goes back to [MyNewsActivity]*/
    @Test
    fun clickBackInSettings_opensMyNewsActivity() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText("Settings")).perform(click())
        intended(hasComponent(SettingsActivity::class.java.name))
        pressBack()
        onView(withId(R.id.newsRecyclerView)).check(matches(isDisplayed()))
    }
}