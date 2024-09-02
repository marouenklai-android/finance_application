package com.example.financeapplication.data.repository

import com.example.financeapplication.data.jsons.StockDetailJson
import com.example.financeapplication.data.jsons.SummaryMarketJson
import com.example.financeapplication.data.service.FinancialMarketService
import retrofit2.Response
import javax.inject.Inject

class FinancialRepository @Inject constructor(private val apiService: FinancialMarketService){

    suspend fun  getMarketSummary(): Response<SummaryMarketJson> {
        return apiService.getMarketSummary()
    }

    suspend fun  getStockDetails(id : String): Response<StockDetailJson> {
        return apiService.getStockDetails(id)
    }
}