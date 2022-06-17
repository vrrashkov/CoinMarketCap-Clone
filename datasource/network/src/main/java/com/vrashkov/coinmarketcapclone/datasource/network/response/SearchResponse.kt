package com.vrashkov.coinmarketcapclone.datasource.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable
import java.math.BigInteger


@Serializable
@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name="coins")
    var coinsList: List<SearchSingleCoin>
 )

@Serializable
@JsonClass(generateAdapter = true)
data class SearchSingleCoin(
    @Json(name="id")
    var id: String,
    @Json(name="name")
    var name: String,
    @Json(name="symbol")
    var symbol: String,
    @Json(name="market_cap_rank")
    var market_cap_rank: Int,
    @Json(name="thumb")
    var thumb: String
)
