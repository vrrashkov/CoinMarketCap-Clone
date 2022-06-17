package com.vrashkov.coinmarketcapclone.datasource.network

import com.vrashkov.coinmarketcapclone.datasource.network.response.*
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") vs_currency: String,
        @Query("order") order: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int,
        @Query("sparkline") sparkline: Boolean,
    ): Response<List<Coin>>

    @GET("global")
    suspend fun getGlobalData(): Response<GlobalDataResponseWrapper>

    @GET("exchanges")
    suspend fun getExchanges(
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): Response<List<ExchangesResponse>>

    @GET("search")
    suspend fun search(
        @Query("query") query: String,
    ): Response<SearchResponse>
}