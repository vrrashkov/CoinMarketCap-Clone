package com.vrashkov.coinmarketcapclone.datasource.domain.datasource.crypto

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

class RemoteCryptoDataSource @Inject constructor(
    private val apiService: ApiService
){
    suspend fun getCryptoLatestList(filter: CryptoCoinsFilterQuery): RequestResult<CoinsResult> = withContext(
        Dispatchers.IO) {
        try{
            val network = apiService.getCoins(
                vs_currency = filter.vs_currency,
                order = filter.order,
                per_page = filter.per_page,
                page = filter.page,
                sparkline = filter.sparkline
            )

            val networkResponse = network.body()!!

            val result = CoinsMapper.mapCoinsResponse(networkResponse)
            return@withContext RequestResult.Success(result)

        } catch (e: Exception) {
            return@withContext (RequestResult.Error(e))
        }
    }
    suspend fun getCryptoExchangesList(filter: CryptoExchangesFilterQuery): RequestResult<ExchangesResult> = withContext(
        Dispatchers.IO) {
        try{
            val network = apiService.getExchanges(
                per_page = filter.per_page,
                page = filter.page
            )

            val networkResponse = network.body()!!

            val result = ExchangesMapper.mapExchangesListResponse(networkResponse)
            return@withContext RequestResult.Success(result)

        } catch (e: Exception) {
            return@withContext (RequestResult.Error(e))
        }
    }

    suspend fun getGlobalData(): RequestResult<GlobalDataResult> = withContext(
        Dispatchers.IO) {
        try{
            val network = apiService.getGlobalData()

            val networkResponse = network.body()!!

            val result = GlobalDataMapper.mapGlobalDataResponse(networkResponse.data)
            return@withContext RequestResult.Success(result)

        } catch (e: Exception) {
            return@withContext (RequestResult.Error(e))
        }
    }

    suspend fun getSearchQuery(query: String): RequestResult<SearchQueryResult> = withContext(
        Dispatchers.IO) {
        try{
            val network = apiService.search(query = query)

            val networkResponse = network.body()!!

            val result = SearchQueryMapper.mapSearchQueryResponse(networkResponse)
            return@withContext RequestResult.Success(result)

        } catch (e: Exception) {
            return@withContext (RequestResult.Error(e))
        }
    }
}