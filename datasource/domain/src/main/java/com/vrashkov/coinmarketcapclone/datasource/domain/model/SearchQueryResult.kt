package com.vrashkov.coinmarketcapclone.datasource.domain.model

import com.squareup.moshi.Json

data class SearchQueryResult (
    var coinsList:MutableList<SearchQuerySingleCoinResult> = mutableListOf(),
)

data class SearchQuerySingleCoinResult(
    var id: String,
    var name: String,
    var symbol: String,
    var marketCcapRrank: Int,
    var thumb: String
)