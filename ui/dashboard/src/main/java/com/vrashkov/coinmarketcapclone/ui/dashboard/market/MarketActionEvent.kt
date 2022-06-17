package com.vrashkov.coinmarketcapclone.ui.dashboard.market

import com.vrashkov.coinmarketcapclone.core.base.ActionsEvent
import com.vrashkov.coinmarketcapclone.core.enums.MarketFilterEnum

sealed class MarketActionEvent : ActionsEvent(){
    data class OpenDialogOptions(val value: MarketFilterEnum): MarketActionEvent()
}