package com.vrashkov.coinmarketcapclone.datasource.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable
import java.math.BigInteger


@Serializable
@JsonClass(generateAdapter = true)
data class ExchangesResponse(
    @Json(name="id")
    var id:String,
    @Json(name="name")
    var name:String,
    @Json(name="image")
    var image:String,
    @Json(name="trust_score")
    var trustScore:Int,
    @Json(name="trust_score_rank")
    var trustScoreRank:Int,
    @Json(name="trade_volume_24h_btc")
    var tradeVolume24Btc:String
 )
