package com.example.financeapplication.app.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.financeapplication.app.livedata.getOrAwaitValue
import com.example.financeapplication.data.jsons.SummaryMarketJson
import com.example.financeapplication.data.repository.FinancialRepository
import com.example.financeapplication.data.service.FinancialMarketService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class FinancialViewModelTest{
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var mainViewModel: FinancialViewModel

    private lateinit var mainRepository: FinancialRepository

    @Mock
    lateinit var apiService: FinancialMarketService

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainRepository = FinancialRepository(apiService)
        mainViewModel = FinancialViewModel(mainRepository)

    }

    @Test
    fun getAllStocksTest() {
        runBlocking {
            Mockito.`when`(mainRepository.getMarketSummary())
                .thenReturn(Response.success(arrayOf<SummaryMarketJson>().getOrNull(0)))
            mainViewModel.getMarketStockList()
            val result = mainViewModel.stockListLD.getOrAwaitValue()
            assertEquals(arrayOf<SummaryMarketJson>().getOrNull(0), result)
        }
    }

    @Test
    fun `empty stock list test`() {
        runBlocking {
            Mockito.`when`(mainRepository.getMarketSummary())
                .thenReturn(Response.success(arrayOf<SummaryMarketJson>()[0]))
            mainViewModel.getMarketStockList()
            val result = mainViewModel.stockListLD.getOrAwaitValue()
            assertEquals(arrayOf<SummaryMarketJson>()[0], result)
        }
    }
}