package com.vrashkov.coinmarketcapclone.datasource.domain.usecase

import androidx.paging.*
import com.vrashkov.coinmarketcapclone.datasource.domain.model.CryptoCoinsFilterQuery
import com.vrashkov.coinmarketcapclone.datasource.domain.model.SingleCoin
import com.vrashkov.coinmarketcapclone.datasource.domain.paging.CoinsPaging
import com.vrashkov.coinmarketcapclone.datasource.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinsListPagingUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) {
    fun execute(pageSize: Int = 20): Flow<PagingData<SingleCoin>> {

        val coins: Flow<PagingData<SingleCoin>> = Pager(PagingConfig(pageSize = pageSize)) {
            CoinsPaging(filter = CryptoCoinsFilterQuery(per_page = pageSize), cryptoRepository = cryptoRepository)
        }.flow

        return coins
    }
}
