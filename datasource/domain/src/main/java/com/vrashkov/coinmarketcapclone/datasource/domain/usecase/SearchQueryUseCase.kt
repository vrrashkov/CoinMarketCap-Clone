package com.vrashkov.coinmarketcapclone.datasource.domain.usecase

import com.vrashkov.coinmarketcapclone.core.base.DataState
import com.vrashkov.coinmarketcapclone.core.base.ProgressBarState
import com.vrashkov.coinmarketcapclone.datasource.domain.model.GlobalDataResult
import com.vrashkov.coinmarketcapclone.datasource.domain.model.SearchQueryResult
import com.vrashkov.coinmarketcapclone.datasource.domain.repository.CryptoRepository
import com.vrashkov.coinmarketcapclone.datasource.domain.repository.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.security.GeneralSecurityException
import javax.inject.Inject

class SearchQueryUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) {
    fun execute(query: String): Flow<DataState<SearchQueryResult>> = flow {

        emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

        when (val searchData = cryptoRepository.getSearchQuery(query = query)) {
            is RequestResult.Success -> {
                emit(DataState.Data(data = searchData.data))
            }
            is RequestResult.Error -> {
                emit(DataState.Error(error = GeneralSecurityException()))
            }
        }

    }
}
