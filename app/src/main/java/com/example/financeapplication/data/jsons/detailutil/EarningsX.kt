package com.example.financeapplication.data.jsons.detailutil

data class EarningsX(
    val earningsChart: EarningsChart,
    val financialCurrency: String,
    val financialsChart: FinancialsChart,
    val maxAge: Int
)