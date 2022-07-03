package com.vrashkov.coinmarketcapclone.ui.profile.authentication.signin.email

import com.vrashkov.coinmarketcapclone.core.base.NavigationEvent

sealed class SignInEmailNavigationEvent: NavigationEvent() {
    object NavigateToRegister: NavigationEvent()
}