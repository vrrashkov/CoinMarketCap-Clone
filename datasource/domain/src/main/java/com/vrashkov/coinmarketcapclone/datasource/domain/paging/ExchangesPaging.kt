package com.vrashkov.coinmarketcapclone.datasource.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vrashkov.coinmarketcapclone.datasource.domain.model.*
import com.vrashkov.coinmarketcapclone.datasource.domain.repository.CryptoRepository
import com.vrashkov.coinmarketcapclone.datasource.domain.repository.RequestResult


class ExchangesPaging constructor(
    private val cryptoRepository: CryptoRepository,
    private val filter: CryptoExchangesFilterQuery
) : PagingSource<Int, SingleExchange>() {
    override fun getRefreshKey(state: PagingState<Int, SingleExchange>): Int?
    {
        return null
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SingleExchange> {
        val nextPage = params.key ?: 1

        return try {
            filter.page = nextPage
            when (val result = cryptoRepository.getCryptoExchangesList(filter = filter)) {
                is RequestResult.Success -> {
                    LoadResult.Page(
                        data = result.data.exchangesList,
                        prevKey = if (nextPage == 1) null else nextPage - 1,
                        nextKey = if (result.data.exchangesList.isEmpty()) null else nextPage + 1
                    )
                }
                is RequestResult.Error -> {
                    LoadResult.Page(
                        data = emptyList(),
                        prevKey = null,
                        nextKey = null
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
