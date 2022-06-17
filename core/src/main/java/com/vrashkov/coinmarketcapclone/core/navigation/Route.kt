package com.vrashkov.coinmarketcapclone.core.navigation

import androidx.navigation.*

const val BASE_ROUTE = "base"
const val DASHBOARD_ROUTE = "dashboard"
const val AUTH_ROUTE = "auth"
const val SETTINGS_ROUTE = "settings"

sealed class Route(val link: String, val arguments: List<NamedNavArgument> = emptyList()) {
    object DashboardMarket: Route(link = "dashboard_market")
    object DashboardExplore: Route(link = "dashboard_explore")
    object DashboardProfile: Route(link = "dashboard_profile")
    object DashboardGravity: Route(link = "dashboard_gravity")

    object AuthSignInEmail: Route(link = "auth_sign_in_email")
    object AuthSignUpEmail: Route(link = "auth_sign_up_email")

    object SettingsMain: Route(link = "settings_main")
}