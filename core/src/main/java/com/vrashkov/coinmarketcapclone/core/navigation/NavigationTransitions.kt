package com.vrashkov.coinmarketcapclone.core.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.Constraints
import androidx.navigation.NavBackStackEntry

@OptIn(ExperimentalAnimationApi::class)
fun exitScreen(constraints: Constraints) : (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)
{
    return ({
        slideOutVertically(
            targetOffsetY = { -(constraints.maxHeight/2) },
            animationSpec = tween(
                durationMillis = 300,
                easing = FastOutSlowInEasing
            )
        ) + fadeOut(animationSpec = tween(300))
    })
}

@OptIn(ExperimentalAnimationApi::class)
fun enterScreen(constraints: Constraints) : (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)
{
    return ({
        slideInVertically(
            initialOffsetY = { -(constraints.maxHeight/2) },
            animationSpec = tween(
                durationMillis = 300,
                easing = FastOutSlowInEasing
            )
        ) + fadeIn(animationSpec = tween(300))
    })
}