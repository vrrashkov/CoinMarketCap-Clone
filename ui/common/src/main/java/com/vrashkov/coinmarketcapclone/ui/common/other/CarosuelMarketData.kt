package com.vrashkov.coinmarketcapclone.ui.common.other

import android.os.Parcelable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrashkov.coinmarketcapclone.core.theme.CoinMarketCapCloneTheme
import kotlinx.coroutines.delay
import kotlinx.parcelize.Parcelize
import java.util.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.vrashkov.coinmarketcapclone.core.enums.LatestMarketDataCarosueltEnum
import com.vrashkov.coinmarketcapclone.ui.common.pureClickable

data class CarosuelLatestMarketData(
    val label: String,
    val subLabel: String,
    val type: LatestMarketDataCarosueltEnum
)

@Composable
fun CarosuelLatestMarketData(
    list: List<CarosuelLatestMarketData> = listOf(),
    onItemClick: (LatestMarketDataCarosueltEnum) -> Unit
) {
    val listState = rememberLazyListState()
    var shownItemPosition by remember { mutableStateOf(0) }

    LaunchedEffect(list.size) {
        while(true) {
            delay(1500)

            if (shownItemPosition == list.size-1) {
                shownItemPosition = 0
            } else {
                shownItemPosition++
            }
            listState.animateScrollToItem(shownItemPosition)
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize(), state = listState)  {
        items(items = list, key = {it.type}) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .pureClickable {
                    onItemClick(it.type)
                },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = it.label,
                    style = CoinMarketCapCloneTheme.typography.display_medium.copy(
                        color = CoinMarketCapCloneTheme.colors.tabNormal,
                        fontSize = 12.sp,
                    )
                )
                Text(
                    text = it.subLabel,
                    style = CoinMarketCapCloneTheme.typography.display_medium.copy(
                        color = CoinMarketCapCloneTheme.colors.tabSelected,
                        fontSize = 11.sp,
                    )
                )
            }
        }
    }
}