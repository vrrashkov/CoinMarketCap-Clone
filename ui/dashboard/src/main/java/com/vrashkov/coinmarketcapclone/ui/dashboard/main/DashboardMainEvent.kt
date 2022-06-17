package com.vrashkov.coinmarketcapclone.ui.dashboard.main

import com.vrashkov.coinmarketcapclone.core.base.BaseViewEvent

sealed class DashboardMainEvent : BaseViewEvent(){
    object MarketClick: DashboardMainEvent()
    object ExploreClick: DashboardMainEvent()
    object PortfolioClick: DashboardMainEvent()
    object GravityClick: DashboardMainEvent()
    
    object OpenDrawer: DashboardMainEvent()

    object LoginClick: DashboardMainEvent()
    object RegisterClick: DashboardMainEvent()
}