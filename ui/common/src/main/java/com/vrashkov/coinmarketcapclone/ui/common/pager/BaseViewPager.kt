package com.vrashkov.coinmarketcapclone.ui.common.pager

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.vrashkov.coinmarketcapclone.core.enums.MarketFilterEnum
import com.vrashkov.coinmarketcapclone.core.enums.MarketPagerEnum
import com.vrashkov.coinmarketcapclone.core.model.MarketActionsPagerData
import com.vrashkov.coinmarketcapclone.core.theme.CoinMarketCapCloneTheme
import com.vrashkov.coinmarketcapclone.ui.common.buttons.SingleImageActionButton

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BaseViewPager(
    pagesCount: Int = 3,
    pagerState: PagerState,
    userScrollEnabled: Boolean = true,
    header: @Composable (() -> Unit)? = null,
    content: @Composable ((Int) -> Unit)? = null
) {

    Column(modifier = Modifier.fillMaxSize()){

        header?.invoke()

        HorizontalPager(
            count = pagesCount,
            state = pagerState,
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            reverseLayout = false,
            userScrollEnabled = userScrollEnabled
        ) { page ->
            content?.invoke(page)
        }
    }
}
