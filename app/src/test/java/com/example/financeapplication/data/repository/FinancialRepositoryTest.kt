package com.example.financeapplication.data.repository

import com.example.financeapplication.data.jsons.SummaryMarketJson
import com.example.financeapplication.data.service.FinancialMarketService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class FinancialRepositoryTest {

    private lateinit var mainRepository: FinancialRepository

    @Mock
    lateinit var apiService: FinancialMarketService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainRepository = FinancialRepository(apiService)
    }

    @Test
    fun `get all stocks test`() {
        runBlocking {
            Mockito.`when`(apiService.getMarketSummary()).thenReturn(Response.success(arrayOf<SummaryMarketJson>().getOrNull(0)))
            val response = mainRepository.getMarketSummary()
            assertEquals(arrayOf<SummaryMarketJson>().getOrNull(0), response.body())
        }
    }

}
