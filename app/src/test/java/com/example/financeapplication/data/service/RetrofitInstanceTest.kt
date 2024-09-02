package com.example.financeapplication.data.service

import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: FinancialMarketService
    private lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(FinancialMarketService::class.java)
    }

    @Test
    fun `get all stocks api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("{}"))
            val response = apiService.getMarketSummary()
            val request = mockWebServer.takeRequest()
            assertEquals("/market/v2/get-summary", request.path)
            assertEquals(true, response.body() != null)
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}