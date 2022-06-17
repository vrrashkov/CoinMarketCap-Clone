package com.vrashkov.coinmarketcapclone.ui.dashboard.market

import com.vrashkov.coinmarketcapclone.core.base.BaseViewEvent
import com.vrashkov.coinmarketcapclone.core.enums.ActionButtonOrderEnum
import com.vrashkov.coinmarketcapclone.core.enums.MarketFilterEnum
import com.vrashkov.coinmarketcapclone.core.enums.MarketFilterSortByEnum

sealed class MarketEvent : BaseViewEvent(){
    data class SortByChange(val newValue: ActionButtonOrderEnum): MarketEvent()
    data class SortBySelectedChange(val newValue: MarketFilterSortByEnum): MarketEvent()
    data class OpenDialogOptions(val value: MarketFilterEnum): MarketEvent()

    data class ChangeCoinFavourite(val symbolId: String, val isFavourite: Boolean): MarketEvent()
}