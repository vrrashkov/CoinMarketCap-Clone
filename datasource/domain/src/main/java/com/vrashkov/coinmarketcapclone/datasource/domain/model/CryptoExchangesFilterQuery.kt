package com.vrashkov.coinmarketcapclone.datasource.domain.model

data class CryptoExchangesFilterQuery (
    var per_page: Int = 20,
    var page: Int = 1
)