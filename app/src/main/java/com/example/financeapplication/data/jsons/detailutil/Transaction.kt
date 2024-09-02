package com.example.financeapplication.data.jsons.detailutil

data class Transaction(
    val filerName: String,
    val filerRelation: String,
    val filerUrl: String,
    val maxAge: Int,
    val moneyText: String,
    val ownership: String,
    val shares: Shares,
    val startDate: StartDate,
    val transactionText: String,
    val value: Value
)