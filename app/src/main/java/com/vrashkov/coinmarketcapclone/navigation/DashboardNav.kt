package com.vrashkov.coinmarketcapclone.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.Constraints
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.google.accompanist.navigation.animation.composable
import com.vrashkov.coinmarketcapclone.core.navigation.*
import com.vrashkov.coinmarketcapclone.ui.dashboard.main.DashboardMainScreen
import com.vrashkov.coinmarketcapclone.ui.dashboard.market.MarketScreen


@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.dashboardNavGraph(
    appState: MultiNavigationAppState,
    route: String,
    constraints: Constraints
){
    navigation(
        startDestination = appState.startDestination,
        route = route
    ){
        market(constraints)
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
fun NavGraphBuilder.market(constraints: Constraints) {
    composable( route = Route.DashboardMarket.link,
        exitTransition = exitScreen(constraints),
        enterTransition = enterScreen(constraints)
    ) {
        MarketScreen()
    }
}
