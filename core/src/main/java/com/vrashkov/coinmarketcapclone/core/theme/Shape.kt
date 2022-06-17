package com.vrashkov.coinmarketcapclone.core.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

object MarketShapes {
    val ActionButtonRoundShape = Shapes(
        small = RoundedCornerShape(12.dp),
        medium = RoundedCornerShape(12.dp),
        large = RoundedCornerShape(12.dp)
    )
    val MarketCapRank = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(4.dp)
    )
}