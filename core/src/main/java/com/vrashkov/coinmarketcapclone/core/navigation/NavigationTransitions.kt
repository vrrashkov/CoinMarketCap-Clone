package com.vrashkov.coinmarketcapclone.core.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.Constraints
import androidx.navigation.NavBackStackEntry
import com.vrashkov.coinmarketcapclone.core.util.log

@OptIn(ExperimentalAnimationApi::class)
fun exitScreen(constraints: Constraints) : (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)
{
    constraints.maxWidth.log{" current screen width" }
    return {
        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(500))
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun enterScreen(constraints: Constraints) : (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)
{
    return {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(500))
    }
}
@OptIn(ExperimentalAnimationApi::class)
fun exitScreenPop(constraints: Constraints) : (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)
{
    constraints.maxWidth.log{" current screen width" }
    return {
        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(500))
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun enterScreenPop(constraints: Constraints) : (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)
{
    return {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(500))
    }
}