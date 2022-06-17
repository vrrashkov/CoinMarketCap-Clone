package com.vrashkov.coinmarketcapclone.datasource.domain.model

data class ExchangesResult (
    val exchangesList: MutableList<SingleExchange> = mutableListOf(),
)
data class SingleExchange (
    var id:String,
    var name:String,
    var image:String,
    var trustScore:Int,
    var trustScoreRank:Int,
    var tradeVolume24Btc:String
)