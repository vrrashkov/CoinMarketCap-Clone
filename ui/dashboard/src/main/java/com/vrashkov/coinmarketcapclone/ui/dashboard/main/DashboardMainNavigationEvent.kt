package com.vrashkov.coinmarketcapclone.ui.dashboard.main

import com.vrashkov.coinmarketcapclone.core.base.NavigationEvent

sealed class DashboardMainNavigationEvent: NavigationEvent() {
    object NavigateToMarketTab: DashboardMainNavigationEvent()
    object NavigateToExploreTab: DashboardMainNavigationEvent()
    object NavigateToPortfolioTab: DashboardMainNavigationEvent()
    object NavigateToGravityTab: DashboardMainNavigationEvent()

    object NavigateToLogin: DashboardMainNavigationEvent()
    object NavigateToRegister: DashboardMainNavigationEvent()
}