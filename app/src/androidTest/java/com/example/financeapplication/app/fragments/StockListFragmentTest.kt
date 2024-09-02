package com.example.financeapplication.app.fragments

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
@MediumTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class StockListFragmentTest{

@get:Rule
val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp(){
        hiltRule.inject()
    }
    @Test
    fun testEventFragment() {
        launchFragmentInHiltContainer<StockListFragment> {
        }
    }
}