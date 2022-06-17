package com.vrashkov.coinmarketcapclone.ui.common.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrashkov.coinmarketcapclone.core.theme.CoinMarketCapCloneTheme
import com.vrashkov.coinmarketcapclone.ui.common.pureClickable

@Composable
fun TextButtonDrawer(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    contentDescription:String = "icon",
    isEnabled: Boolean = false
) {
    val icon = rememberVectorPainter(image = icon)

    var modifier = if (!isEnabled) {
        Modifier.alpha(0.3f)
    } else {
        Modifier.pureClickable {
            onClick()
        }
    }

    Row(
        modifier = modifier.fillMaxWidth().height(40.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            modifier = Modifier.size(28.dp).pureClickable {
                onClick()
            },
            painter = icon,
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(CoinMarketCapCloneTheme.colors.secondary)
        )

        Text(
            text = text,
            style = CoinMarketCapCloneTheme.typography.display_heavy.copy(
                color = CoinMarketCapCloneTheme.colors.tabNormal,
                fontSize = 18.sp,
            )
        )
    }
}