package com.vrashkov.coinmarketcapclone.ui.dashboard.market

import android.view.ContextThemeWrapper
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.CalendarView
import android.widget.LinearLayout
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vrashkov.coinmarketcapclone.core.enums.ActionButtonOrderEnum
import com.vrashkov.coinmarketcapclone.core.enums.MarketFilterEnum
import com.vrashkov.coinmarketcapclone.core.enums.MarketFilterSortByEnum
import com.vrashkov.coinmarketcapclone.core.enums.MarketPagerEnum
import com.vrashkov.coinmarketcapclone.core.model.MarketActionsPagerData
import com.vrashkov.coinmarketcapclone.core.model.MarketListItem
import com.vrashkov.coinmarketcapclone.core.model.OptionsDialogItem
import com.vrashkov.coinmarketcapclone.core.navigation.DASHBOARD_ROUTE
import com.vrashkov.coinmarketcapclone.core.navigation.LocalNavigationState
import com.vrashkov.coinmarketcapclone.core.theme.CoinMarketCapCloneTheme
import com.vrashkov.coinmarketcapclone.core.theme.MarketColors
import com.vrashkov.coinmarketcapclone.core.theme.MarketShapes
import com.vrashkov.coinmarketcapclone.core.util.convertToNumberWithSuffix
import com.vrashkov.coinmarketcapclone.core.util.getSharedViewModelParentEntry
import com.vrashkov.coinmarketcapclone.core.util.log
import com.vrashkov.coinmarketcapclone.core.util.round
import com.vrashkov.coinmarketcapclone.datasource.domain.model.SingleCoin
import com.vrashkov.coinmarketcapclone.datasource.domain.model.SingleExchange
import com.vrashkov.coinmarketcapclone.ui.common.buttons.SingleImageActionButton
import com.vrashkov.coinmarketcapclone.ui.common.buttons.TextActionButton
import com.vrashkov.coinmarketcapclone.ui.common.buttons.TextOrderActionButton
import com.vrashkov.coinmarketcapclone.ui.common.dialog.OptionsDialog
import com.vrashkov.coinmarketcapclone.ui.common.header.MarketHeader
import com.vrashkov.coinmarketcapclone.ui.common.other.CarosuelLatestMarketData
import com.vrashkov.coinmarketcapclone.ui.common.pager.BaseViewPager
import com.vrashkov.coinmarketcapclone.ui.common.pureClickable
import com.vrashkov.coinmarketcapclone.ui.dashboard.glucoses.MarketState
import com.vrashkov.coinmarketcapclone.ui.dashboard.main.*
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

@ExperimentalMaterialApi
@Composable
fun MarketScreen() {

    val baseAppState = LocalNavigationState.baseNavigation
    val dashboardAppState = LocalNavigationState.dashboardNavigation

    val baseNavController: NavHostController = baseAppState.navController
    val dashboardNavController: NavHostController = dashboardAppState.navController

    val viewModelMarket = hiltViewModel<MarketVM>()

    val onTriggerMarketEvents = viewModelMarket::onTriggerEvent
    val viewStateMarket = viewModelMarket.viewState.value

    val parentEntry = getSharedViewModelParentEntry(DASHBOARD_ROUTE)
    val dashboardScreenViewModel = hiltViewModel<DashboardMainVM>(parentEntry)
    val dashboardScreenOnTriggerEvents = dashboardScreenViewModel::onTriggerEvent

    var showOptionsDialogSortBy by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModelMarket.actionsEventFlow.collect {
            when (it) {
                is MarketActionEvent.OpenDialogOptions -> {
                    when(it.value) {
                        MarketFilterEnum.CoinSortBy -> showOptionsDialogSortBy = true
                    }
                }
            }
        }
    }

    MarketScreenComponent(
        viewModelMarket = viewModelMarket,
        viewStateMarket = viewStateMarket,
        onTriggerMarketEvents = onTriggerMarketEvents,
        dashboardScreenOnTriggerEvents = dashboardScreenOnTriggerEvents
    )

    OptionsDialog(
        title = "Sort By",
        showModalTransitionDialog = showOptionsDialogSortBy,
        onDismissRequest = {
            showOptionsDialogSortBy = false
        },
        selectedItem = viewStateMarket.sortBySelected,
        optionslist = listOf(
            OptionsDialogItem(label = MarketFilterSortByEnum.MarketCap.label, data = MarketFilterSortByEnum.MarketCap),
            OptionsDialogItem(label = MarketFilterSortByEnum.Volume.label, data = MarketFilterSortByEnum.Volume)
        ),
        onSelectItem = {
            onTriggerMarketEvents(MarketEvent.SortBySelectedChange(it.data))
        }
    )
}

@Composable
private fun MarketScreenComponent(
    viewModelMarket: MarketVM,
    viewStateMarket: MarketState,
    onTriggerMarketEvents: (MarketEvent) -> Unit,
    dashboardScreenOnTriggerEvents: (DashboardMainEvent) -> Unit
) {


    Column(modifier = Modifier.fillMaxSize()){
        MarketHeader(
            onProfileClick = {
                dashboardScreenOnTriggerEvents(DashboardMainEvent.OpenDrawer)
            },
            onCartClick = {

            },
            onSearchClick = {

            },
            centerContent = {
                CarosuelLatestMarketData(
                    list = viewStateMarket.lastMarketDataList,
                    onItemClick = {

                    }
                )
            }
        )

        val viewPagerTabsList = listOf(
            MarketPagerEnum.Cryptocurrencies,
            MarketPagerEnum.Exchanges
        )


        val coinImagePlaceholder = rememberVectorPainter(image = Icons.Rounded.Apps)
        val upArrowImage = rememberVectorPainter(image = Icons.Rounded.ArrowDropUp)
        val downArrowImage = rememberVectorPainter(image = Icons.Rounded.ArrowDropDown)
        val favouritesImage = rememberVectorPainter(image = Icons.Rounded.StarBorder)
        val favouritesImageFilled = rememberVectorPainter(image = Icons.Rounded.Star)
        val coroutineScope = rememberCoroutineScope()
        val lazyCoinsItems: LazyPagingItems<SingleCoin> = viewModelMarket.lazyCoinsItems.collectAsLazyPagingItems()
        val lazyExchangeItems: LazyPagingItems<SingleExchange> = viewModelMarket.lazyExchangesItems.collectAsLazyPagingItems()

        val lazyCoinsIsRefreshing = lazyCoinsItems.loadState.refresh is LoadState.Loading
        val lazyExchangeIsRefreshing = lazyExchangeItems.loadState.refresh is LoadState.Loading


        MarketViewPager(
            pageTitles = viewPagerTabsList,
            onTriggerMarketEvents = onTriggerMarketEvents,
            onPageChange = {

            },
            content = { page ->
                when (viewPagerTabsList[page]) {
                    MarketPagerEnum.Cryptocurrencies -> {
                        Box(Modifier.fillMaxSize()) {
                            SwipeRefresh(
                                state = rememberSwipeRefreshState(lazyCoinsIsRefreshing),
                                onRefresh = {
                                    lazyCoinsItems.refresh()
                                },
                            ) {
                                LazyColumn(modifier = Modifier.fillMaxSize()) {
                                        if (!lazyCoinsIsRefreshing) {
                                        items(items = lazyCoinsItems) { coin ->

                                            coin?.let {
                                                MarketListItem(
                                                    item = MarketListItem(
                                                        marketType = MarketPagerEnum.Cryptocurrencies,
                                                        unique_id = it.id,
                                                        name = it.name,
                                                        imageUrl = it.imageUrl,
                                                        rank = it.marketCapRank,
                                                        shortCode = it.symbol,
                                                        currentPrice = it.currentPrice,
                                                        sparklingGraph = it.price,
                                                        totalMarketCap = it.marketCap,
                                                        priceChangePercentage = it.priceChangePercentage
                                                    ),
                                                    imagePlaceholder = coinImagePlaceholder,
                                                    upArrowImage = upArrowImage,
                                                    downArrowImage = downArrowImage,
                                                    favouritesImage = favouritesImage,
                                                    onFavouriteClick = {

                                                    }
                                                )
                                                Divider(color = CoinMarketCapCloneTheme.colors.content)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    MarketPagerEnum.Exchanges -> {
                        Box(Modifier.fillMaxSize()) {
                            SwipeRefresh(
                                state = rememberSwipeRefreshState(lazyExchangeIsRefreshing),
                                onRefresh = { lazyExchangeItems.refresh() },
                            ) {
                                LazyColumn(modifier = Modifier.fillMaxSize()) {
                                    if (!lazyExchangeIsRefreshing) {
                                        items(items = lazyExchangeItems) { exchange ->
                                            exchange?.let {
                                                MarketListItem(
                                                    item = MarketListItem(
                                                        marketType = MarketPagerEnum.Exchanges,
                                                        unique_id = it.id,
                                                        name = it.name,
                                                        imageUrl = it.image,
                                                        rank = it.trustScoreRank,
                                                        shortCode = null,
                                                        currentPrice = null,
                                                        sparklingGraph = listOf(),
                                                        totalMarketCap = it.tradeVolume24Btc.toDouble()
                                                            .toLong().toString(),
                                                        priceChangePercentage = null
                                                    ),
                                                    imagePlaceholder = coinImagePlaceholder,
                                                    upArrowImage = upArrowImage,
                                                    downArrowImage = downArrowImage,
                                                    favouritesImage = favouritesImage,
                                                    onFavouriteClick = {

                                                    }
                                                )
                                                Divider(color = CoinMarketCapCloneTheme.colors.content)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            },
            onActionClick = {
                onTriggerMarketEvents(MarketEvent.OpenDialogOptions(value = it))
            }
        )
    }
}
@Composable
fun MarketListItem(
    item: MarketListItem,
    imagePlaceholder: VectorPainter,
    upArrowImage: VectorPainter,
    downArrowImage: VectorPainter,
    favouritesImage: VectorPainter,
    onFavouriteClick: (String) -> Unit
) {
    Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp).fillMaxWidth().height(45.dp), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = imagePlaceholder,
            contentDescription = "coin image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(34.dp).clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(10.dp))
        Row(modifier = Modifier.fillMaxHeight(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
                Row (modifier = Modifier){
                    Text(
                        text = item.name,
                        modifier = Modifier.width(110.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = CoinMarketCapCloneTheme.typography.display_heavy.copy(
                            color = CoinMarketCapCloneTheme.colors.secondary,
                            fontSize = 16.sp,
                        )
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(modifier = Modifier.height(20.dp).width(90.dp).padding(horizontal = 8.dp)) {
                        item.priceChangePercentage?.let{
                            SparklingChart(isPositive = (it > 0), prices = item.sparklingGraph)
                        }
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.Bottom) {
                    Box(modifier = Modifier
                        .background(color = CoinMarketCapCloneTheme.colors.content, shape = MarketShapes.MarketCapRank.small)
                        .padding(horizontal = 4.dp)
                    ) {
                        item.rank?.let {
                            Text(
                                text = it.toString(),//item.marketCapRank.toString(),
                                style = CoinMarketCapCloneTheme.typography.display_heavy.copy(
                                    color = CoinMarketCapCloneTheme.colors.aboveContent,
                                    fontSize = 13.sp,
                                )
                            )
                        }
                    }

                    item.shortCode?.let {
                        Text(
                            text = it.uppercase(),
                            style = CoinMarketCapCloneTheme.typography.display_heavy.copy(
                                color = CoinMarketCapCloneTheme.colors.aboveContent,
                                fontSize = 13.sp,
                            )
                        )
                    }
                    Row(modifier = Modifier, verticalAlignment = Alignment.Bottom) {
                        item.priceChangePercentage?.let {
                            var arrowIcon = upArrowImage
                            var arrowColor = CoinMarketCapCloneTheme.colors.correct
                            if (it < 0) {
                                arrowIcon = downArrowImage
                                arrowColor = CoinMarketCapCloneTheme.colors.error
                            }
                            Image(
                                modifier = Modifier.width(18.dp).offset(y = 2.dp).align(Alignment.Bottom),
                                painter = arrowIcon,
                                contentDescription = "percentage icon",
                                contentScale = ContentScale.FillHeight,
                                colorFilter = ColorFilter.tint(arrowColor)
                            )
                            Text(
                                text = "${it.toDouble().round(3)}%",
                                style = CoinMarketCapCloneTheme.typography.display_heavy.copy(
                                    color = CoinMarketCapCloneTheme.colors.aboveContent,
                                    fontSize = 13.sp,
                                )
                            )
                        }
                    }
                }
            }
            Row(modifier = Modifier.fillMaxHeight(), horizontalArrangement = Arrangement.Center){
                Column(modifier = Modifier.width(80.dp).fillMaxHeight(),verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.End){
                    item.currentPrice?.let {
                        Text(
                            text = "US$${NumberFormat.getNumberInstance(Locale.US).format(it)}",
                            modifier = Modifier.width(80.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = CoinMarketCapCloneTheme.typography.display_heavy.copy(
                                color = CoinMarketCapCloneTheme.colors.aboveContentDeeper,
                                fontSize = 13.sp,
                                textAlign = TextAlign.End
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    item.totalMarketCap?.let {
                        Text(
                            text = "${it.convertToNumberWithSuffix()}",
                            modifier = Modifier.width(80.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = CoinMarketCapCloneTheme.typography.display_heavy.copy(
                                color = CoinMarketCapCloneTheme.colors.tabNormal,
                                fontSize = 13.sp,
                                textAlign = TextAlign.End
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    modifier = Modifier.size(24.dp).align(Alignment.CenterVertically).pureClickable {
                        onFavouriteClick(item.unique_id)
                    },
                    painter = favouritesImage,
                    contentDescription = "favourites icon",
                    colorFilter = ColorFilter.tint(CoinMarketCapCloneTheme.colors.tabNormal)
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MarketViewPager(
    onTriggerMarketEvents: (MarketEvent) -> Unit,
    pageTitles: List<MarketPagerEnum>,
    orderOptions: List<List<MarketActionsPagerData>?> = listOf(
        listOf(
            MarketActionsPagerData(type = MarketFilterEnum.CoinFavourites, isEnabled = true, label = "Favourites"),
            MarketActionsPagerData(type = MarketFilterEnum.CoinWishlist, label = "Wishlist"),
            MarketActionsPagerData(type = MarketFilterEnum.CoinCurrency, "Currency"),
            MarketActionsPagerData(type = MarketFilterEnum.CoinSortBy, order = ActionButtonOrderEnum.Desc, isEnabled = true, label = "Sort by"),
            MarketActionsPagerData(type = MarketFilterEnum.CoinTime, "Change Timeline"),
            MarketActionsPagerData(type = MarketFilterEnum.CoinLookingFor, "Looking for")
        ),
        listOf(
            MarketActionsPagerData(type = MarketFilterEnum.ExchangeFavourites, isEnabled = true, label = "Favourites"),
            MarketActionsPagerData(type = MarketFilterEnum.ExchangeCurrency, "Currency"),
            MarketActionsPagerData(type = MarketFilterEnum.ExchangeSortBy, label =  "Sort by"),
            MarketActionsPagerData(type = MarketFilterEnum.ExchangeTime, "Change Timeline")
        ),
        null
    ),
    onPageChange: (MarketPagerEnum) -> Unit,
    onActionClick: (MarketFilterEnum) -> Unit,
    content: @Composable ((Int) -> Unit)? = null
) {

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0)
    val currentOptions = orderOptions.getOrNull(pagerState.currentPage)

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onPageChange(pageTitles[page])
        }
    }

    BaseViewPager(
        pagesCount = pageTitles.size,
        pagerState = pagerState,
        header = {
            Column {
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(items = pageTitles) { index, item ->

                        val labelColor = if (index == pagerState.currentPage) {
                            CoinMarketCapCloneTheme.colors.secondary
                        } else {
                            CoinMarketCapCloneTheme.colors.tabNormal
                        }

                        Text(
                            modifier = Modifier.clickable {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }

                                onPageChange(item)
                            },
                            text = item.name,
                            style = CoinMarketCapCloneTheme.typography.display_heavy.copy(
                                color = labelColor,
                                fontSize = 16.sp,
                            )
                        )
                    }
                }
                currentOptions?.let {
                    LazyRow(
                        modifier = Modifier.height(30.dp),
                        contentPadding = PaddingValues(horizontal = 15.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)){
                        items(items = it) { actionItem ->
                            if(actionItem.type == MarketFilterEnum.CoinFavourites || actionItem.type == MarketFilterEnum.ExchangeFavourites) {
                                SingleImageActionButton(
                                    isButtonEnabled = actionItem.isEnabled,
                                    icon = Icons.Rounded.StarBorder,
                                    onClick = {
                                        onActionClick(actionItem.type)
                                    }
                                )
                            } else {
                                if (actionItem.order == null) {
                                    TextActionButton(
                                        isButtonEnabled = actionItem.isEnabled,
                                        label = actionItem.label,
                                        onClick = {
                                            onActionClick(actionItem.type)
                                        }
                                    )
                                } else {
                                    TextOrderActionButton(
                                        isButtonEnabled = actionItem.isEnabled,
                                        label = actionItem.label,
                                        defaultOrder = actionItem.order!!,
                                        onClick = {
                                            onActionClick(actionItem.type)
                                        },
                                        onOrderClick = {
                                            onTriggerMarketEvents(MarketEvent.SortByChange(it))
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        content = { page ->
            content?.invoke(page)
        }
    )
}

@Composable
fun SparklingChart(
    isPositive: Boolean,
    prices: List<Float>
) {

    val pricesChartData: ArrayList<Entry> = arrayListOf()

    prices.forEachIndexed { index, value ->
        pricesChartData.add(Entry(index.toFloat(), value))
    }
    val data =  LineDataSet(pricesChartData, "prices")
    data.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
    data.setDrawFilled(true)
    data.setDrawValues(false)
    data.setDrawIcons(false)
    data.setDrawCircles(false)
    data.setDrawCircleHole(false)
    if (isPositive) {
        data.setColor(CoinMarketCapCloneTheme.colors.correct.hashCode())
        data.setFillColor(CoinMarketCapCloneTheme.colors.correct.hashCode())
    } else {
        data.setColor(CoinMarketCapCloneTheme.colors.error.hashCode())
        data.setFillColor(CoinMarketCapCloneTheme.colors.error.hashCode())
    }

    data.lineWidth = 1f

    data.fillAlpha = 110

    val dataSet: ArrayList<ILineDataSet> = arrayListOf()
    dataSet.add(data)

    val lineData: LineData = LineData(dataSet)

    AndroidView(
        modifier = Modifier.wrapContentSize(),
        factory = { context ->
            LineChart(
                context
            ).apply {

                setData(lineData)
                layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                setDrawMarkers(false)
                setDrawBorders(false)
                setViewPortOffsets(0f, 0f, 0f, 0f);
                setDrawGridBackground(false)
                axisLeft.setDrawGridLines(false)
                axisRight.setDrawGridLines(false)
                axisLeft.setDrawLabels(false)
                axisRight.setDrawLabels(false)
                axisLeft.setDrawAxisLine(false)
                axisRight.setDrawAxisLine(false)
                xAxis.setDrawGridLines(false)
                xAxis.setDrawLabels(false)
                xAxis.setDrawAxisLine(false)

                legend.isEnabled = false
                description.isEnabled = false
                setTouchEnabled(false)
                isDragEnabled = false
                isScaleXEnabled = false
                isScaleYEnabled = false
                isHighlightPerDragEnabled = false
                setPinchZoom(false)
                animateX(500)
            }
        }
    )
}