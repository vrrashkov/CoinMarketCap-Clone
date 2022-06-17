package com.vrashkov.coinmarketcapclone.datasource.domain.repository

import com.vrashkov.coinmarketcapclone.core.base.DataState
import com.vrashkov.coinmarketcapclone.core.base.ProgressBarState
import com.vrashkov.coinmarketcapclone.datasource.domain.datasource.crypto.LocalCryptoDataSource
import com.vrashkov.coinmarketcapclone.datasource.domain.datasource.crypto.RemoteCryptoDataSource
import com.vrashkov.coinmarketcapclone.datasource.domain.mappers.CoinsMapper
import com.vrashkov.coinmarketcapclone.datasource.domain.model.*
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val remoteCoinsDataSource: RemoteCryptoDataSource,
    private val localCoinsDataSource: LocalCryptoDataSource
) {
    suspend fun getCryptoLatestList(filter: CryptoCoinsFilterQuery): RequestResult<CoinsResult> {
        return remoteCoinsDataSource.getCryptoLatestList(filter = filter)
    }
    suspend fun getGlobalData(): RequestResult<GlobalDataResult> {
        return remoteCoinsDataSource.getGlobalData()
    }
    suspend fun getCryptoExchangesList(filter: CryptoExchangesFilterQuery): RequestResult<ExchangesResult> {
        return remoteCoinsDataSource.getCryptoExchangesList(filter = filter)
    }
    suspend fun getSearchQuery(query: String): RequestResult<SearchQueryResult> {
        return remoteCoinsDataSource.getSearchQuery(query = query)
    }
    suspend fun updateCoinFavourite(uniqueId: String, isFavourite: Boolean): RequestResult<Boolean> {
        return localCoinsDataSource.updateFavouriteCoin(symbolId = uniqueId, isFavourite = isFavourite)
    }
}