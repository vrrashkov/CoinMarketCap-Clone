package com.vrashkov.coinmarketcapclone.datasource.domain.mappers

import com.vrashkov.coinmarketcapclone.datasource.domain.model.CoinsResult
import com.vrashkov.coinmarketcapclone.datasource.domain.model.GlobalDataResult
import com.vrashkov.coinmarketcapclone.datasource.domain.model.SingleCoin
import com.vrashkov.coinmarketcapclone.datasource.network.response.Coin
import com.vrashkov.coinmarketcapclone.datasource.network.response.GlobalDataResponse
import java.math.BigInteger

class GlobalDataMapper {
    companion object {
        fun mapGlobalDataResponse(entity: GlobalDataResponse): GlobalDataResult {

            return GlobalDataResult(
                activeCryptoCurrencies = entity.active_crypto_currencies,
                markets = entity.markets,
                totalMarketCap = entity.total_market_cap,
                totalVolume = entity.total_volume,
                marketCapPercentage = entity.market_cap_percentage,
                marketCapChangePercentage24hUsd = entity.market_cap_change_percentage_24h_usd,
            )
        }
    }
}