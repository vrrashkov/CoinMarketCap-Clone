package com.vrashkov.coinmarketcapclone.ui.common.header

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScreenHeader(
    leftContent: @Composable (() -> Unit)? = null,
    centerContent: @Composable (() -> Unit)? = null,
    rightContent: @Composable (() -> Unit)? = null,
    arrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().height(37.dp),
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box (modifier = Modifier.fillMaxHeight().wrapContentWidth()) {
            leftContent?.invoke()
        }

        Box (modifier = Modifier.fillMaxHeight().weight(1f)) {
            centerContent?.invoke()
        }

        Box (modifier = Modifier.fillMaxHeight()) {
            rightContent?.invoke()
        }
    }
}