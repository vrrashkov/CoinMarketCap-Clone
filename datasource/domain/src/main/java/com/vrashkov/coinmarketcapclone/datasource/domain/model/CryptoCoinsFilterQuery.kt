package com.vrashkov.coinmarketcapclone.datasource.domain.model

data class CryptoCoinsFilterQuery (
    var vs_currency: String = "usd",
    var order: String = "market_cap_desc",
    var per_page: Int = 20,
    var page: Int = 1,
    var sparkline: Boolean = true
)