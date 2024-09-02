package com.example.financeapplication.data.jsons.detailutil

data class Holder(
    val latestTransDate: LatestTransDate,
    val maxAge: Int,
    val name: String,
    val positionDirect: PositionDirect,
    val positionDirectDate: PositionDirectDate,
    val relation: String,
    val transactionDescription: String,
    val url: String
)