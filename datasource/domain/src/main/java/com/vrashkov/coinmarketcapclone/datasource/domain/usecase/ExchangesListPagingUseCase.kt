package com.vrashkov.coinmarketcapclone.datasource.domain.usecase

import androidx.paging.*
import com.vrashkov.coinmarketcapclone.datasource.domain.model.CryptoExchangesFilterQuery
import com.vrashkov.coinmarketcapclone.datasource.domain.model.SingleExchange
import com.vrashkov.coinmarketcapclone.datasource.domain.paging.ExchangesPaging
import com.vrashkov.coinmarketcapclone.datasource.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExchangesListPagingUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) {
    fun execute(pageSize: Int = 20): Flow<PagingData<SingleExchange>> {

        val exchanges: Flow<PagingData<SingleExchange>> = Pager(PagingConfig(pageSize = pageSize)) {
            ExchangesPaging(filter = CryptoExchangesFilterQuery(per_page = pageSize), cryptoRepository = cryptoRepository)
        }.flow

        return exchanges
    }
}
