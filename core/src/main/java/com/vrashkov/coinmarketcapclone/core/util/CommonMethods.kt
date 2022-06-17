package com.vrashkov.coinmarketcapclone.core.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.vrashkov.coinmarketcapclone.core.navigation.LocalNavigationState
import kotlin.math.round

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun getSharedViewModelParentEntry(route: String) : NavBackStackEntry {
    val rootAppState = LocalNavigationState.baseNavigation
    val rootNavController: NavHostController = rootAppState.navController
    val parentEntry = remember {
        rootNavController.getBackStackEntry(route)
    }

    return parentEntry
}

const val MILLION = 1000000L
const val BILLION = 1000000000L
const val TRILLION = 1000000000000L

fun String.convertToNumberWithSuffix(): String? {
    val x = this.toLong()
    return when {
        x < MILLION -> x.toString()
        x < BILLION -> "${x.times(100).div(MILLION).times(0.01)} M"
        x < TRILLION -> "${x.times(100).div(BILLION).times(0.01)} Bn"
        else -> "${x.times(100).div(TRILLION).times(0.01)} T"
    }
}