package com.example.financeapplication.data.jsons

import com.example.financeapplication.data.jsons.detailutil.CalendarEvents
import com.example.financeapplication.data.jsons.detailutil.DefaultKeyStatistics
import com.example.financeapplication.data.jsons.detailutil.Details
import com.example.financeapplication.data.jsons.detailutil.EarningsX
import com.example.financeapplication.data.jsons.detailutil.EsgScores
import com.example.financeapplication.data.jsons.detailutil.FinancialData
import com.example.financeapplication.data.jsons.detailutil.FundOwnership
import com.example.financeapplication.data.jsons.detailutil.InsiderHolders
import com.example.financeapplication.data.jsons.detailutil.InsiderTransactions
import com.example.financeapplication.data.jsons.detailutil.InstitutionOwnership
import com.example.financeapplication.data.jsons.detailutil.MajorDirectHolders
import com.example.financeapplication.data.jsons.detailutil.MajorHoldersBreakdown
import com.example.financeapplication.data.jsons.detailutil.NetSharePurchaseActivity
import com.example.financeapplication.data.jsons.detailutil.PageViews
import com.example.financeapplication.data.jsons.detailutil.Price
import com.example.financeapplication.data.jsons.detailutil.QuoteType
import com.example.financeapplication.data.jsons.detailutil.RecommendationTrend
import com.example.financeapplication.data.jsons.detailutil.SummaryDetail
import com.example.financeapplication.data.jsons.detailutil.SummaryProfile
import com.example.financeapplication.data.jsons.detailutil.UpgradeDowngradeHistory


data class StockDetailJson(
    val calendarEvents: CalendarEvents,
    val defaultKeyStatistics: DefaultKeyStatistics,
    val details: Details,
    val earnings: EarningsX,
    val esgScores: EsgScores,
    val financialData: FinancialData,
    val fundOwnership: FundOwnership,
    val insiderHolders: InsiderHolders,
    val insiderTransactions: InsiderTransactions,
    val institutionOwnership: InstitutionOwnership,
    val majorDirectHolders: MajorDirectHolders,
    val majorHoldersBreakdown: MajorHoldersBreakdown,
    val netSharePurchaseActivity: NetSharePurchaseActivity,
    val pageViews: PageViews,
    val price: Price,
    val quoteType: QuoteType,
    val recommendationTrend: RecommendationTrend,
    val summaryDetail: SummaryDetail,
    val summaryProfile: SummaryProfile,
    val symbol: String,
    val upgradeDowngradeHistory: UpgradeDowngradeHistory
)