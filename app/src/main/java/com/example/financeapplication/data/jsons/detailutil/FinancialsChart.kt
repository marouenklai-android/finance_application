package com.example.financeapplication.data.jsons.detailutil

data class FinancialsChart(
    val quarterly: List<QuarterlyX>,
    val yearly: List<Yearly>
)