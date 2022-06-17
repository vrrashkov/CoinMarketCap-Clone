package com.vrashkov.coinmarketcapclone.core.model


data class MarketListItem(
    val unique_id: String,
    val name: String,
    val imageUrl: String,
    val rank: Int?,
    val shortCode: String?,
    val sparklingGraph: List<Float> = listOf(),
    val currentPrice: Float?,
    val totalMarketCap: String?,
    val priceChangePercentage:Float?,
)