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
import com.vrashkov.coinmarketcapclone.ui.profile.authentication.signin.email.SignInEmailScreen
import com.vrashkov.coinmarketcapclone.ui.profile.authentication.signup.email.SignUpEmailScreen
import com.vrashkov.coinmarketcapclone.ui.profile.settings.main.SettingsMainScreen


@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.settingsNavGraph(
    appState: MultiNavigationAppState,
    route: String,
    constraints: Constraints
){
    navigation(
        startDestination = appState.startDestination,
        route = route
    ){
        SettingsMain(constraints)
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
fun NavGraphBuilder.SettingsMain(constraints: Constraints) {
    composable( route = Route.SettingsMain.link,
        exitTransition = exitScreen(constraints),
        enterTransition = enterScreen(constraints)
    ) {
        SettingsMainScreen()
    }
}