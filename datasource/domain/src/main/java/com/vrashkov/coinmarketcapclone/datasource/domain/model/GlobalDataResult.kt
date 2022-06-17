package com.vrashkov.coinmarketcapclone.datasource.domain.model

import com.squareup.moshi.Json

data class GlobalDataResult (
    var activeCryptoCurrencies:String,
    var markets:String,
    var totalMarketCap:Map<String,String>,
    var totalVolume:Map<String,String>,
    var marketCapPercentage:Map<String,String>,
    var marketCapChangePercentage24hUsd:String,
)