package com.vrashkov.coinmarketcapclone.datasource.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable
import java.math.BigInteger


@Serializable
@JsonClass(generateAdapter = true)
data class GlobalDataResponseWrapper(
    @Json(name="data")
    var data:GlobalDataResponse,
)

@Serializable
@JsonClass(generateAdapter = true)
data class GlobalDataResponse(
    @Json(name="active_cryptocurrencies")
    var active_crypto_currencies:String,
    @Json(name="markets")
    var markets:String,
    @Json(name="total_market_cap")
    var total_market_cap:Map<String,String>,
    @Json(name="total_volume")
    var total_volume:Map<String,String>,
    @Json(name="market_cap_percentage")
    var market_cap_percentage:Map<String,String>,
    @Json(name="market_cap_change_percentage_24h_usd")
    var market_cap_change_percentage_24h_usd:String,
 )
