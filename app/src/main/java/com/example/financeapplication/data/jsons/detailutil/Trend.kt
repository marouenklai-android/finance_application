package com.example.financeapplication.data.jsons.detailutil

data class Trend(
    val buy: Int,
    val hold: Int,
    val period: String,
    val sell: Int,
    val strongBuy: Int,
    val strongSell: Int
)