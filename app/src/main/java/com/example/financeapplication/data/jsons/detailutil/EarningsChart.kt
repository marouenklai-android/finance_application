package com.example.financeapplication.data.jsons.detailutil

data class EarningsChart(
    val currentQuarterEstimate: CurrentQuarterEstimate,
    val currentQuarterEstimateDate: String,
    val currentQuarterEstimateYear: Int,
    val earningsDate: List<Any>,
    val quarterly: List<Quarterly>
)