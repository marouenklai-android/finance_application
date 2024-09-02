package com.example.financeapplication.data.service

import com.example.financeapplication.data.jsons.StockDetailJson
import com.example.financeapplication.data.jsons.SummaryMarketJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface FinancialMarketService {

    @GET("market/v2/get-summary")
    suspend fun getMarketSummary():Response<SummaryMarketJson>

    @GET("stock/v2/get-summary")
    suspend fun getStockDetails(@Query("symbol") id :String):Response<StockDetailJson>

}