package com.example.financeapplication.data.jsons.detailutil

data class OwnershipX(
    val maxAge: Int,
    val organization: String,
    val pctHeld: PctHeld,
    val position: Position,
    val reportDate: ReportDate,
    val value: Value
)