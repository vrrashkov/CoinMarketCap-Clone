package com.vrashkov.coinmarketcapclone.datasource.domain.datasource.crypto

import com.vrashkov.coinmarketcapclone.datasource.database.dao.CoinsDao
import com.vrashkov.coinmarketcapclone.datasource.database.entity.CoinsEntity
import com.vrashkov.coinmarketcapclone.datasource.domain.mappers.CoinsMapper
import com.vrashkov.coinmarketcapclone.datasource.domain.mappers.ExchangesMapper
import com.vrashkov.coinmarketcapclone.datasource.domain.mappers.GlobalDataMapper
import com.vrashkov.coinmarketcapclone.datasource.domain.mappers.SearchQueryMapper
import com.vrashkov.coinmarketcapclone.datasource.domain.model.*
import com.vrashkov.coinmarketcapclone.datasource.domain.repository.RequestResult
import com.vrashkov.coinmarketcapclone.datasource.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalCryptoDataSource @Inject constructor(
    private val coinsDao: CoinsDao,
){

    suspend fun updateFavouriteCoin(symbolId: String, isFavourite: Boolean): RequestResult<Boolean> = withContext(Dispatchers.IO) {

        var isUpdated = coinsDao.updateFavouriteCoin(symbolId = symbolId, isFavourite = isFavourite)

        if (isUpdated > 0) {} else {
            coinsDao.insert(
                CoinsEntity(
                    symbol_id = symbolId,
                    isFavourite = isFavourite
                )
            )
        }

        return@withContext RequestResult.Success(isFavourite)
    }
}