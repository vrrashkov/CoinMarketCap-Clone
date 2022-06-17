package com.vrashkov.coinmarketcapclone.core.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberMultiNavigationAppState(
    startDestination: String,
    navController: NavHostController = rememberAnimatedNavController()
) = remember(navController, startDestination) {
    MultiNavigationAppState(navController, startDestination)
}


class MultiNavigationAppState(
    private var _navController: NavHostController? = null,
    private val _startDestination: String? = null,
) {

    fun navigateBack() {
        _navController!!.popBackStack()
    }

    var startDestination: String = _startDestination!!

    var navController: NavHostController = _navController!!
}

data class MultiNavigationStates(
    var baseNavigation: MultiNavigationAppState = MultiNavigationAppState(),
    var dashboardNavigation: MultiNavigationAppState = MultiNavigationAppState(),
    var authNavigation: MultiNavigationAppState = MultiNavigationAppState(),
    var settingsNavigation: MultiNavigationAppState = MultiNavigationAppState()
)

lateinit var LocalNavigationState: MultiNavigationStates