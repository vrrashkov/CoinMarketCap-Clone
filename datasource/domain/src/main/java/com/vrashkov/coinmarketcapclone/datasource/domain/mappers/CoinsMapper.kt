package com.vrashkov.coinmarketcapclone.datasource.domain.mappers

import com.vrashkov.coinmarketcapclone.datasource.domain.model.CoinsResult
import com.vrashkov.coinmarketcapclone.datasource.domain.model.SingleCoin
import com.vrashkov.coinmarketcapclone.datasource.network.response.Coin
import java.math.BigInteger

class CoinsMapper {
    companion object {
        fun mapCoinsResponse(entities: List<Coin>): CoinsResult {

            var coinsResult = CoinsResult()

            entities.forEach {
                coinsResult.coinsList.add(
                    SingleCoin(
                        id = it.id,
                        symbol = it.symbol,
                        name = it.name,
                        price = it.sparkline_in_7d.price,
                        imageUrl = it.image,

                        marketCapRank = it.market_cap_rank.toInt(),
                        currentPrice = it.current_price.toFloat(),
                        marketCap = it.market_cap,
                        priceChangePercentage = it.price_change_percentage_24h.toFloat(),
                        marketCapChangePercentage = it.market_cap_change_percentage_24h.toFloat()
                    )
                )
            }


            return coinsResult
        }
    }
}