package com.vrashkov.coinmarketcapclone.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

fun Route.args(map:Map<String, String>) : String {
    var location: String = this.link.substringBefore('?')

    this.arguments.forEach{
        if (map.containsKey(it.name)) {
            if (!location.contains("?")) {
                location += "?" + it.name + "=" + map[it.name];
            } else {
                location += "&" + it.name + "=" + map[it.name];
            }
        }
    }

    return location
}

fun String.routeFromArgs(arguments: Map<String, String>) : String {
    var location: String = this.substringBefore('?')

    arguments.forEach{
        if (!location.contains("?")) {
            location += "?" + it.key + "=" + arguments[it.key];
        } else {
            location += "&" + it.key + "=" + arguments[it.key];
        }
    }

    return location
}

@Composable
fun NavHostController.getDestination() : Sequence<NavDestination>? {
    val navBackStackEntry by this.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    return currentDestination?.hierarchy
}

fun Sequence<NavDestination>?.isSelectedRoute(route: String, arguments: Map<String, String>? = null) : Boolean {
    if (this == null) {
        return false
    }

    return this.any {
        var currentRoute:String = it.route!!

        if (arguments != null) {
            currentRoute = currentRoute.routeFromArgs(arguments)
        }

        val sameDestination: Boolean = currentRoute.equals(route)

        sameDestination
    }

}