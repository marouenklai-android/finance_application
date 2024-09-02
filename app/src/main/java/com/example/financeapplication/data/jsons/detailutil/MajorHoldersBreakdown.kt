package com.example.financeapplication.data.jsons.detailutil

data class MajorHoldersBreakdown(
    val insidersPercentHeld: InsidersPercentHeld,
    val institutionsCount: InstitutionsCount,
    val institutionsFloatPercentHeld: InstitutionsFloatPercentHeld,
    val institutionsPercentHeld: InstitutionsPercentHeld,
    val maxAge: Int
)