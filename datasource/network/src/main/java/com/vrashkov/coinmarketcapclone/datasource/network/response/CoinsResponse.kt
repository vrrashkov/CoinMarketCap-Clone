package com.vrashkov.coinmarketcapclone.datasource.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable
import java.math.BigInteger


@Serializable
@JsonClass(generateAdapter = true)
data class Coin(
    @Json(name="id")
    var id:String,
    @Json(name="symbol")
    var symbol:String,
    @Json(name="name")
    var name:String,
    @Json(name="image")
    var image:String,
    @Json(name="current_price")
    var current_price:String,
    @Json(name="market_cap")
    var market_cap:String,
    @Json(name="market_cap_rank")
    var market_cap_rank:String,
    @Json(name="market_cap_change_percentage_24h")
    var price_change_percentage_24h:String,
    @Json(name="price_change_percentage_24h")
    var market_cap_change_percentage_24h:String,
    @Json(name="sparkline_in_7d")
    var sparkline_in_7d:CoinSparkline,


    )
@Serializable
@JsonClass(generateAdapter = true)
data class CoinSparkline(
    @Json(name="price")
    var price:List<Float>
)
