package com.vrashkov.coinmarketcapclone.ui.dashboard.glucoses

import com.vrashkov.coinmarketcapclone.core.base.BaseViewState
import com.vrashkov.coinmarketcapclone.core.enums.ActionButtonOrderEnum
import com.vrashkov.coinmarketcapclone.core.enums.LatestMarketDataCarosueltEnum
import com.vrashkov.coinmarketcapclone.core.enums.MarketFilterSortByEnum
import com.vrashkov.coinmarketcapclone.ui.common.other.CarosuelLatestMarketData


data class MarketState(
    val lastMarketDataList: List<CarosuelLatestMarketData> = listOf(),
    val sortByOrder: ActionButtonOrderEnum = ActionButtonOrderEnum.Desc,
    val sortBySelected: MarketFilterSortByEnum = MarketFilterSortByEnum.MarketCap
): BaseViewState()
