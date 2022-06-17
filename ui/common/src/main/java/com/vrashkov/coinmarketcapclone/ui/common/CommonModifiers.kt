package com.vrashkov.coinmarketcapclone.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import com.vrashkov.coinmarketcapclone.core.theme.MarketColors

inline fun Modifier.pureClickable(crossinline onClick: ()->Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

inline fun Modifier.rippleClickable(color: Color, crossinline onClick: ()->Unit): Modifier = composed {
    clickable(
        indication = rememberRipple(bounded = true, color = color),
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

