package com.example.financeapplication.app.fragments

import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.financeapplication.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@HiltAndroidTest
@MediumTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class StockItemDetailFragmentTest{

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp(){
        hiltRule.inject()
    }
    @Test
    fun testFragmentLaunch() {
        val fragmentArgs = bundleOf("stockItemSymbol" to "")
        launchFragmentInHiltContainer<StockItemDetailFragment>(fragmentArgs) {
        }



    }
    @Test
    fun testViewVisibilityInFragment() {
        val fragmentArgs = bundleOf("stockItemSymbol" to "")
        launchFragmentInHiltContainer<StockItemDetailFragment>(fragmentArgs) {
        }

        Espresso.onView(ViewMatchers.withId(R.id.title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
}