package com.example.financeapplication.data.jsons.detailutil

data class Earnings(
    val earningsAverage: EarningsAverage,
    val earningsDate: List<Any>,
    val earningsHigh: EarningsHigh,
    val earningsLow: EarningsLow,
    val revenueAverage: RevenueAverage,
    val revenueHigh: RevenueHigh,
    val revenueLow: RevenueLow
)