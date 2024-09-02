package com.example.financeapplication.data.jsons.detailutil

data class InsiderTransactions(
    val maxAge: Int,
    val transactions: List<Transaction>
)