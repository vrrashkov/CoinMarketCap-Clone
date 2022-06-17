package com.vrashkov.coinmarketcapclone.core.actions

data class DashboardTabBarActions (
    val marketClick: (() -> Unit) = {},
    val exploreClick: (() -> Unit) = {},
    val portfolioClick: (() -> Unit) = {},
    val gravityClick: (() -> Unit) = {}
)