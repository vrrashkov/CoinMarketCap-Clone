package com.vrashkov.coinmarketcapclone.ui.dashboard.main

import com.vrashkov.coinmarketcapclone.core.base.ActionsEvent

sealed class DashboardMainActionEvent : ActionsEvent(){
    object OpenDrawer: DashboardMainActionEvent()
}