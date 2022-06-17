package com.vrashkov.coinmarketcapclone.datasource.domain.mappers

import com.squareup.moshi.Json
import com.vrashkov.coinmarketcapclone.datasource.domain.model.CoinsResult
import com.vrashkov.coinmarketcapclone.datasource.domain.model.ExchangesResult
import com.vrashkov.coinmarketcapclone.datasource.domain.model.SingleCoin
import com.vrashkov.coinmarketcapclone.datasource.domain.model.SingleExchange
import com.vrashkov.coinmarketcapclone.datasource.network.response.Coin
import com.vrashkov.coinmarketcapclone.datasource.network.response.ExchangesResponse
import java.math.BigInteger

class ExchangesMapper {
    companion object {
        fun mapExchangesListResponse(entities: List<ExchangesResponse>): ExchangesResult {

            var exchangesResult = ExchangesResult()

            entities.forEach {
                exchangesResult.exchangesList.add(
                    SingleExchange(
                        id = it.id,
                        name = it.name,
                        image = it.image,
                        trustScore = it.trustScore,
                        trustScoreRank = it.trustScoreRank,
                        tradeVolume24Btc = it.tradeVolume24Btc
                    )
                )
            }

            return exchangesResult
        }
    }
}