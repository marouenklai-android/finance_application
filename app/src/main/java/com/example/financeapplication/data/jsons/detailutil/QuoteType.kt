package com.example.financeapplication.data.jsons.detailutil

data class QuoteType(
    val exchange: String,
    val exchangeTimezoneName: String,
    val exchangeTimezoneShortName: String,
    val gmtOffSetMilliseconds: String,
    val headSymbol: Any,
    val isEsgPopulated: Boolean,
    val longName: String,
    val market: String,
    val messageBoardId: String,
    val quoteType: String,
    val shortName: String,
    val symbol: String,
    val underlyingExchangeSymbol: Any,
    val underlyingSymbol: Any,
    val uuid: String
)