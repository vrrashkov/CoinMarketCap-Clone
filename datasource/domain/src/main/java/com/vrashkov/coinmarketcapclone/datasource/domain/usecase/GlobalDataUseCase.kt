package com.vrashkov.coinmarketcapclone.datasource.domain.usecase

import com.vrashkov.coinmarketcapclone.core.base.DataState
import com.vrashkov.coinmarketcapclone.core.base.ProgressBarState
import com.vrashkov.coinmarketcapclone.datasource.domain.model.GlobalDataResult
import com.vrashkov.coinmarketcapclone.datasource.domain.repository.CryptoRepository
import com.vrashkov.coinmarketcapclone.datasource.domain.repository.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.security.GeneralSecurityException
import javax.inject.Inject

class GlobalDataUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) {
    fun execute(): Flow<DataState<GlobalDataResult>> = flow {

        emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

        when (val globaData = cryptoRepository.getGlobalData()) {
            is RequestResult.Success -> {
                emit(DataState.Data(data = globaData.data))
            }
            is RequestResult.Error -> {
                emit(DataState.Error(error = GeneralSecurityException()))
            }
        }

    }
}
