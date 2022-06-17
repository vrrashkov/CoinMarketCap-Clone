package com.vrashkov.coinmarketcapclone.datasource.domain.usecase

import com.vrashkov.coinmarketcapclone.core.base.DataState
import com.vrashkov.coinmarketcapclone.core.base.ProgressBarState
import com.vrashkov.coinmarketcapclone.datasource.domain.repository.CryptoRepository
import com.vrashkov.coinmarketcapclone.datasource.domain.repository.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.security.GeneralSecurityException
import javax.inject.Inject

class ChangeCoinFavouriteUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) {
    fun execute(symbolId: String, isFavourite: Boolean): Flow<DataState<Boolean>> = flow {

        emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

        when (val globaData = cryptoRepository.updateCoinFavourite(uniqueId = symbolId, isFavourite = isFavourite)) {
            is RequestResult.Success -> {
                emit(DataState.Data(data = globaData.data))
            }
            is RequestResult.Error -> {
                emit(DataState.Error(error = GeneralSecurityException()))
            }
        }

    }
}
