package com.vrashkov.coinmarketcapclone.datasource.domain.mappers

import com.vrashkov.coinmarketcapclone.datasource.domain.model.*
import com.vrashkov.coinmarketcapclone.datasource.network.response.Coin
import com.vrashkov.coinmarketcapclone.datasource.network.response.GlobalDataResponse
import com.vrashkov.coinmarketcapclone.datasource.network.response.SearchResponse
import java.math.BigInteger

class SearchQueryMapper {
    companion object {
        fun mapSearchQueryResponse(entity: SearchResponse): SearchQueryResult {

            var coinsList: MutableList<SearchQuerySingleCoinResult> = mutableListOf()

            entity.coinsList.forEach {
                coinsList.add(SearchQuerySingleCoinResult(
                id = it.id,
                name = it.name,
                symbol = it.symbol,
                marketCcapRrank = it.market_cap_rank,
                thumb = it.thumb
                ))
            }

            return SearchQueryResult(
                coinsList = coinsList
            )
        }
    }
}