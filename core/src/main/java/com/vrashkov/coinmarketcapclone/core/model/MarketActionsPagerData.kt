package com.vrashkov.coinmarketcapclone.core.model

import com.vrashkov.coinmarketcapclone.core.enums.ActionButtonOrderEnum
import com.vrashkov.coinmarketcapclone.core.enums.MarketFilterEnum

data class MarketActionsPagerData(
    val type: MarketFilterEnum,
    val label: String,
    val order: ActionButtonOrderEnum? = null,
    val isEnabled: Boolean = false
)