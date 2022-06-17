package com.vrashkov.coinmarketcapclone.ui.common.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrashkov.coinmarketcapclone.core.theme.CoinMarketCapCloneTheme
import com.vrashkov.coinmarketcapclone.ui.common.pureClickable

@Composable
fun BackButtonHeader(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier
) {
    val arrowBackPainter = rememberVectorPainter(image = Icons.Rounded.ArrowBack)

    ScreenHeader(
        modifier = modifier,
        arrangement = Arrangement.Start,
        leftContent = {
            Image(
                modifier = Modifier.size(28.dp).pureClickable {
                    onBackClick()
                },
                painter = arrowBackPainter,
                contentDescription = "back button",
                colorFilter = ColorFilter.tint(CoinMarketCapCloneTheme.colors.tabNormal)
            )
        },
        centerContent = {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = title,
                style = CoinMarketCapCloneTheme.typography.display_heavy.copy(
                    color = CoinMarketCapCloneTheme.colors.tabNormal,
                    fontSize = 18.sp,
                )
            )
        }
    )
}