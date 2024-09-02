package com.example.financeapplication.data.jsons.detailutil

data class NetSharePurchaseActivity(
    val buyInfoCount: BuyInfoCount,
    val buyInfoShares: BuyInfoShares,
    val buyPercentInsiderShares: BuyPercentInsiderShares,
    val maxAge: Int,
    val netInfoCount: NetInfoCount,
    val netInfoShares: NetInfoShares,
    val netPercentInsiderShares: NetPercentInsiderShares,
    val period: String,
    val sellInfoCount: SellInfoCount,
    val sellInfoShares: SellInfoShares,
    val sellPercentInsiderShares: SellPercentInsiderShares,
    val totalInsiderShares: TotalInsiderShares
)