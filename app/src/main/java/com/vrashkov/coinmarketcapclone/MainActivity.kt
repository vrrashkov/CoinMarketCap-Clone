package com.vrashkov.coinmarketcapclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Constraints
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vrashkov.coinmarketcapclone.core.base.NavigationEvent
import com.vrashkov.coinmarketcapclone.core.navigation.*
import com.vrashkov.coinmarketcapclone.core.theme.CoinMarketCapCloneTheme
import com.vrashkov.coinmarketcapclone.navigation.authNavGraph
import com.vrashkov.coinmarketcapclone.navigation.dashboardNavGraph
import com.vrashkov.coinmarketcapclone.navigation.settingsNavGraph
import com.vrashkov.coinmarketcapclone.ui.dashboard.main.DashboardMainScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CoinMarketCapCloneTheme {
                BoxWithConstraints(
                    modifier = Modifier.navigationBarsPadding().imePadding()
                ) {
                    AppWrapper(constraints = constraints)
                }
            }
        }
    }
}
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun AppWrapper(constraints: Constraints) {
    LocalNavigationState = MultiNavigationStates(
        baseNavigation = rememberMultiNavigationAppState(startDestination = BASE_ROUTE),

        dashboardNavigation = rememberMultiNavigationAppState(startDestination = Route.DashboardMarket.link),
        authNavigation = rememberMultiNavigationAppState(startDestination = Route.AuthSignInEmail.link),
        settingsNavigation = rememberMultiNavigationAppState(startDestination = Route.SettingsMain.link)
    )


    AnimatedNavHost(
        navController = LocalNavigationState.baseNavigation.navController,
        startDestination = DASHBOARD_ROUTE,
        route = BASE_ROUTE
    ) {
        composable(
            DASHBOARD_ROUTE
        ) {
            LocalNavigationState.dashboardNavigation.navController = rememberAnimatedNavController()

            DashboardMainScreen(
                mainContent = {
                    AnimatedNavHost(
                        navController = LocalNavigationState.dashboardNavigation.navController,
                        startDestination = DASHBOARD_ROUTE,
                        route = BASE_ROUTE
                    ) {
                        dashboardNavGraph(
                            appState = LocalNavigationState.dashboardNavigation,
                            route = DASHBOARD_ROUTE,
                            constraints = constraints
                        )
                    }
                }
            )
        }
        composable(
            AUTH_ROUTE
        ) {
            LocalNavigationState.authNavigation.navController = rememberAnimatedNavController()

            AnimatedNavHost(
                navController = LocalNavigationState.authNavigation.navController,
                startDestination = AUTH_ROUTE,
                route = BASE_ROUTE
            ) {
                authNavGraph(
                    appState = LocalNavigationState.authNavigation,
                    route = AUTH_ROUTE,
                    constraints = constraints
                )
            }
        }
        composable(
            SETTINGS_ROUTE
        ) {
            LocalNavigationState.settingsNavigation.navController = rememberAnimatedNavController()

            AnimatedNavHost(
                navController = LocalNavigationState.settingsNavigation.navController,
                startDestination = SETTINGS_ROUTE,
                route = BASE_ROUTE
            ) {
                settingsNavGraph(
                    appState = LocalNavigationState.settingsNavigation,
                    route = SETTINGS_ROUTE,
                    constraints = constraints
                )
            }
        }
    }

}