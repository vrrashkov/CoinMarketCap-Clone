package com.vrashkov.coinmarketcapclone.ui.dashboard.market

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.vrashkov.coinmarketcapclone.core.base.BaseViewModel
import com.vrashkov.coinmarketcapclone.core.base.DataState
import com.vrashkov.coinmarketcapclone.core.enums.LatestMarketDataCarosueltEnum
import com.vrashkov.coinmarketcapclone.core.util.convertToNumberWithSuffix
import com.vrashkov.coinmarketcapclone.core.util.round
import com.vrashkov.coinmarketcapclone.datasource.domain.model.SingleCoin
import com.vrashkov.coinmarketcapclone.datasource.domain.model.SingleExchange
import com.vrashkov.coinmarketcapclone.datasource.domain.usecase.ChangeCoinFavouriteUseCase
import com.vrashkov.coinmarketcapclone.datasource.domain.usecase.CoinsListPagingUseCase
import com.vrashkov.coinmarketcapclone.datasource.domain.usecase.ExchangesListPagingUseCase
import com.vrashkov.coinmarketcapclone.datasource.domain.usecase.GlobalDataUseCase
import com.vrashkov.coinmarketcapclone.ui.common.other.CarosuelLatestMarketData
import com.vrashkov.coinmarketcapclone.ui.dashboard.glucoses.MarketState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketVM
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val coinsListPagingUseCase: CoinsListPagingUseCase,
    private val exchangesListPagingUseCase: ExchangesListPagingUseCase,
    private val changeCoinFavouriteUseCase: ChangeCoinFavouriteUseCase,
    private val globalDataUseCase: GlobalDataUseCase
): BaseViewModel<MarketState, MarketEvent>(){

    override val viewState: MutableState<MarketState> = mutableStateOf(MarketState())

    lateinit var lazyCoinsItems: Flow<PagingData<SingleCoin>>
    lateinit var lazyExchangesItems: Flow<PagingData<SingleExchange>>

    init {
        GlobalData()
        ChangeCoinsData()
        ChangeExchangeData()
    }

    override fun onTriggerEvent(event: MarketEvent){
        when (event) {
            is MarketEvent.SortByChange -> {
                viewState.value = viewState.value.copy(
                        sortByOrder = event.newValue
                )
            }
            is MarketEvent.SortBySelectedChange -> {
                viewState.value = viewState.value.copy(
                    sortBySelected = event.newValue
                )
                ChangeCoinsData()
            }

            is MarketEvent.OpenDialogOptions -> viewModelScope.launch {
                _actionsEventFlow.emit(MarketActionEvent.OpenDialogOptions(event.value))
            }

            is MarketEvent.ChangeCoinFavourite -> viewModelScope.launch {
                changeCoinFavouriteUseCase.execute(symbolId = event.symbolId, isFavourite = !event.isFavourite).collect { data ->
                    when(data) {
                        is DataState.Data -> {

                        }
                        is DataState.Error -> {

                        }
                        is DataState.Loading -> {

                        }
                    }
                }
            }
        }
    }

    private fun GlobalData() {
        globalDataUseCase.execute().onEach {
            when (it) {
                is DataState.Data -> {
                    val lastMarketDataList: MutableList<CarosuelLatestMarketData> = mutableListOf()
                    it.data?.let {
                        lastMarketDataList.add(
                            CarosuelLatestMarketData(type = LatestMarketDataCarosueltEnum.TotalMarketsCount,
                                label = LatestMarketDataCarosueltEnum.TotalMarketsCount.label,
                                subLabel = it.markets
                            )
                        )
                        lastMarketDataList.add(
                            CarosuelLatestMarketData(type = LatestMarketDataCarosueltEnum.TotalActiveCryptoCurrencies,
                                label = LatestMarketDataCarosueltEnum.TotalActiveCryptoCurrencies.label,
                                subLabel = it.activeCryptoCurrencies
                            )
                        )
                        if (it.marketCapPercentage.containsKey("btc")) {
                            it.marketCapPercentage.get("btc")?.let { percentage ->
                                lastMarketDataList.add(
                                    CarosuelLatestMarketData(type = LatestMarketDataCarosueltEnum.MarketCapPercentage,
                                        label = String.format(LatestMarketDataCarosueltEnum.MarketCapPercentage.label, "BTC"),
                                        subLabel = percentage.toDouble().round(3).toString()
                                    )
                                )
                            }
                        }
                        if (it.totalVolume.containsKey("btc")) {
                            it.totalVolume.get("btc")?.let { volume ->
                                lastMarketDataList.add(
                                    CarosuelLatestMarketData(type = LatestMarketDataCarosueltEnum.TotalVolume,
                                        label = String.format(LatestMarketDataCarosueltEnum.TotalVolume.label, "BTC"),
                                        subLabel = volume.toDouble().toLong().toString().convertToNumberWithSuffix()!!
                                    )
                                )
                            }
                        }
                        if (it.totalMarketCap.containsKey("eth")) {
                            it.totalMarketCap.get("eth")?.let { volume ->
                                lastMarketDataList.add(
                                    CarosuelLatestMarketData(type = LatestMarketDataCarosueltEnum.MCapVolume,
                                        label = String.format(LatestMarketDataCarosueltEnum.MCapVolume.label, "ETH"),
                                        subLabel = volume.toDouble().toLong().toString().convertToNumberWithSuffix().toString()
                                    )
                                )
                            }
                        }
                    }

                    viewState.value = viewState.value.copy(
                        lastMarketDataList = lastMarketDataList
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun ChangeExchangeData() {
        lazyExchangesItems = exchangesListPagingUseCase.execute()
    }
    private fun ChangeCoinsData() {
        lazyCoinsItems = coinsListPagingUseCase.execute()
    }
}


