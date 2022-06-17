package com.vrashkov.coinmarketcapclone.ui.profile.authentication.signin.email

import com.vrashkov.coinmarketcapclone.core.base.BaseViewState


data class SignInEmailState(
    val email: String = "",
    val password: String = ""
): BaseViewState()
