package com.vrashkov.coinmarketcapclone.datasource.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vrashkov.coinmarketcapclone.datasource.domain.model.CryptoCoinsFilterQuery
import com.vrashkov.coinmarketcapclone.datasource.domain.model.SingleCoin
import com.vrashkov.coinmarketcapclone.datasource.domain.repository.CryptoRepository
import com.vrashkov.coinmarketcapclone.datasource.domain.repository.RequestResult


class CoinsPaging constructor(
    private val cryptoRepository: CryptoRepository,
    private val filter: CryptoCoinsFilterQuery
) : PagingSource<Int, SingleCoin>() {
    override fun getRefreshKey(state: PagingState<Int, SingleCoin>): Int?
    {
        return null
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SingleCoin> {
        val nextPage = params.key ?: 1

        return try {
            filter.page = nextPage
            when (val result = cryptoRepository.getCryptoLatestList(filter = filter)) {
                is RequestResult.Success -> {
                    LoadResult.Page(
                        data = result.data.coinsList,
                        prevKey = if (nextPage == 1) null else nextPage - 1,
                        nextKey = if (result.data.coinsList.isEmpty()) null else nextPage + 1
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
