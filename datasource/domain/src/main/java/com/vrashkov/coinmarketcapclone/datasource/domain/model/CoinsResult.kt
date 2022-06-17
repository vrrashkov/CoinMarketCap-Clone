package com.vrashkov.coinmarketcapclone.datasource.domain.model

data class CoinsResult (
    val coinsList: MutableList<SingleCoin> = mutableListOf(),
)
data class SingleCoin (
    val id: String,
    val marketCapRank: Int,
    val currentPrice: Float,
    val marketCap: String,
    val priceChangePercentage: Float,
    val marketCapChangePercentage: Float,
    val symbol: String,
    val name: String,
    val imageUrl: String,
    val price:List<Float>
)