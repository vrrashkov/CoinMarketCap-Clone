package com.vrashkov.coinmarketcapclone.ui.dashboard.main

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vrashkov.coinmarketcapclone.core.actions.DashboardTabBarActions
import com.vrashkov.coinmarketcapclone.core.base.NavigationEvent
import com.vrashkov.coinmarketcapclone.core.navigation.*
import com.vrashkov.coinmarketcapclone.core.theme.CoinMarketCapCloneTheme
import com.vrashkov.coinmarketcapclone.ui.common.buttons.TextButtonDrawer
import com.vrashkov.coinmarketcapclone.ui.common.pureClickable
import com.vrashkov.coinmarketcapclone.ui.common.rippleClickable
import com.vrashkov.coinmarketcapclone.ui.dashboard.glucoses.DashboardMainState
import com.vrashkov.coinmarketcapclone.ui.dashboard.glucoses.MarketState


@ExperimentalMaterialApi
@Composable
fun DashboardMainScreen(mainContent: @Composable (ColumnScope.() -> Unit)? = null) {

    val baseAppState = LocalNavigationState.baseNavigation
    val dashboardAppState = LocalNavigationState.dashboardNavigation
    val authAppState = LocalNavigationState.authNavigation

    val baseNavController: NavHostController = baseAppState.navController
    val dashboardNavController: NavHostController = dashboardAppState.navController

    val viewModel = hiltViewModel<DashboardMainVM>()

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(Unit) {
        viewModel.navigationEventFlow.collect {
            when (it) {
                is NavigationEvent.NavigateBack -> dashboardNavController.popBackStack()
                is DashboardMainNavigationEvent.NavigateToMarketTab -> {
                    dashboardNavController.navigate(route = Route.DashboardMarket.link) {
                        launchSingleTop = true
                    }
                }
                is DashboardMainNavigationEvent.NavigateToExploreTab -> {
                    authAppState.startDestination = Route.AuthSignInEmail.link
                    baseNavController.navigate(route = AUTH_ROUTE)
                }
                is DashboardMainNavigationEvent.NavigateToPortfolioTab -> {
                    authAppState.startDestination = Route.AuthSignInEmail.link
                    baseNavController.navigate(route = AUTH_ROUTE)
                }
                is DashboardMainNavigationEvent.NavigateToGravityTab -> {
                    authAppState.startDestination = Route.AuthSignInEmail.link
                    baseNavController.navigate(route = AUTH_ROUTE)
                }
                is DashboardMainNavigationEvent.NavigateToLogin -> {
                    authAppState.startDestination = Route.AuthSignInEmail.link
                    baseNavController.navigate(route = AUTH_ROUTE)
                }
                is DashboardMainNavigationEvent.NavigateToRegister -> {
                    authAppState.startDestination = Route.AuthSignUpEmail.link
                    baseNavController.navigate(route = AUTH_ROUTE)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.actionsEventFlow.collect {
            when (it) {
                is DashboardMainActionEvent.OpenDrawer -> {
                    scaffoldState.drawerState.open()
                }
            }
        }
    }

    val onTriggerEvents = viewModel::onTriggerEvent
    val viewState = viewModel.viewState.value


    DashboardMainScreenLayout(
        scaffoldState = scaffoldState,
        viewState = viewState,
        mainContent = mainContent,
        dashboardTabBarActions = DashboardTabBarActions(
            marketClick = {
                onTriggerEvents(DashboardMainEvent.MarketClick)
            },
            exploreClick = {
                onTriggerEvents(DashboardMainEvent.ExploreClick)
            },
            portfolioClick = {
                onTriggerEvents(DashboardMainEvent.PortfolioClick)
            },
            gravityClick = {
                onTriggerEvents(DashboardMainEvent.GravityClick)
            }
        ),
        onTriggerEvents = onTriggerEvents
    )

}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun DashboardMainScreenLayout(
    scaffoldState: ScaffoldState,
    viewState: DashboardMainState,
    mainContent: @Composable (ColumnScope.() -> Unit)? = null,
    onTriggerEvents: (DashboardMainEvent) -> Unit,
    dashboardTabBarActions: DashboardTabBarActions = DashboardTabBarActions()
) {
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DashboardDrawer(onTriggerEvents = onTriggerEvents)
        }
    ) { paddingValues ->
        ConstraintLayout (
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (mainRef, bottomRef) = createRefs()

            Column(
                modifier = Modifier
                    .constrainAs(mainRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(bottomRef.top)
                        height = Dimension.fillToConstraints
                    }
            ) {
                mainContent?.invoke(this)
            }
            Column(
                modifier = Modifier.constrainAs(bottomRef) {
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                DashboardScreenTabBar(dashboardTabBarActions = dashboardTabBarActions)
            }

        }
    }
}

@Composable
private fun DashboardScreenTabBar(dashboardTabBarActions: DashboardTabBarActions = DashboardTabBarActions()) {

    val dashboardNavController: NavHostController = LocalNavigationState.dashboardNavigation.navController
    val navDestination = dashboardNavController.getDestination()

    val marketsIcon = rememberVectorPainter(image = Icons.Rounded.Storefront)
    val explore = rememberVectorPainter(image = Icons.Rounded.TravelExplore)
    val portfolio = rememberVectorPainter(image = Icons.Rounded.DonutSmall)
    val gravity = rememberVectorPainter(image = Icons.Rounded.Mms)

    Row (modifier = Modifier.height(60.dp).fillMaxWidth()) {
        DashboardScreenTabBarItem(
            isSelected = (navDestination.isSelectedRoute(Route.DashboardMarket.link)),
            icon = marketsIcon,
            label = "Markets",
            contentDescription = "markets tab",
            onClick = {
                dashboardTabBarActions.marketClick()
            }
        )

        DashboardScreenTabBarItem(
            isSelected = (navDestination.isSelectedRoute(Route.DashboardExplore.link)),
            icon = explore,
            label = "Explore",
            contentDescription = "explore tab",
            onClick = {
                dashboardTabBarActions.exploreClick()
            }
        )

        DashboardScreenTabBarItem(
            isSelected = (navDestination.isSelectedRoute(Route.DashboardProfile.link)),
            icon = portfolio,
            label = "Portfolio",
            contentDescription = "portfolio tab",
            onClick = {
                dashboardTabBarActions.portfolioClick()
            }
        )

        DashboardScreenTabBarItem(
            isSelected = (navDestination.isSelectedRoute(Route.DashboardGravity.link)),
            icon = gravity,
            label = "Gravity",
            contentDescription = "gravity tab",
            onClick = {
                dashboardTabBarActions.gravityClick()
            }
        )
    }
}
@Composable
private fun RowScope.DashboardScreenTabBarItem(
    contentDescription: String = "",
    label: String,
    icon: Painter,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {

    Column (
        modifier = Modifier
            .fillMaxHeight().weight(1f)
            .rippleClickable(color = CoinMarketCapCloneTheme.colors.tabSelected) {
                onClick()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            modifier = Modifier.size(30.dp),
            painter = icon,
            contentDescription = contentDescription,
            colorFilter = if (isSelected) {
                ColorFilter.tint(CoinMarketCapCloneTheme.colors.tabSelected)
            } else {
                ColorFilter.tint(CoinMarketCapCloneTheme.colors.tabNormal)
            }
        )
        Text(
            text = label,
            style = CoinMarketCapCloneTheme.typography.display_medium.copy(
                color = if (isSelected) {
                    CoinMarketCapCloneTheme.colors.tabSelected
                } else {
                   CoinMarketCapCloneTheme.colors.tabNormal
                },
                fontSize = 11.sp,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun DashboardDrawer(
    onTriggerEvents: (DashboardMainEvent) -> Unit
) {
    val imageUrl: String? = "https://s2.coinmarketcap.com/static/img/coins/64x64/1839.png"
    val accountImagePlaceholder = rememberVectorPainter(image = Icons.Rounded.AccountCircle)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            placeholder = accountImagePlaceholder,
            contentDescription = "profile image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(80.dp).clip(CircleShape)
        )

        Row(modifier = Modifier.padding(top = 15.dp), horizontalArrangement = Arrangement.Center){
            Text(
                modifier = Modifier.pureClickable {
                    onTriggerEvents(DashboardMainEvent.LoginClick)
                },
                text = "Log In",
                style = CoinMarketCapCloneTheme.typography.display_heavy.copy(
                    color = CoinMarketCapCloneTheme.colors.tabNormal,
                    fontSize = 28.sp,
                )
            )
            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = "/",
                style = CoinMarketCapCloneTheme.typography.display_heavy.copy(
                    color = CoinMarketCapCloneTheme.colors.tabNormal,
                    fontSize = 28.sp,
                )
            )
            Text(
                modifier = Modifier.pureClickable {
                    onTriggerEvents(DashboardMainEvent.RegisterClick)
                },
                text = "Register",
                style = CoinMarketCapCloneTheme.typography.display_heavy.copy(
                    color = CoinMarketCapCloneTheme.colors.tabNormal,
                    fontSize = 28.sp,
                )
            )
        }

        Column(
            modifier = Modifier.padding(top = 20.dp, start = 15.dp, end = 15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            TextButtonDrawer(
                icon = Icons.Rounded.Handyman,
                text = "Tools",
                contentDescription = "tools icon",
                onClick = {}
            )
            TextButtonDrawer(
                icon = Icons.Rounded.Settings,
                text = "Settings",
                contentDescription = "settings icon",
                onClick = {},
                isEnabled = true
            )
            TextButtonDrawer(
                icon = Icons.Rounded.HelpCenter,
                text = "Help Center",
                contentDescription = "help center icon",
                onClick = {}
            )
            TextButtonDrawer(
                icon = Icons.Rounded.People,
                text = "About Us",
                contentDescription = "about us icon",
                onClick = {}
            )
        }
    }
}