package com.vrashkov.coinmarketcapclone.ui.profile.authentication.signup.email

import com.vrashkov.coinmarketcapclone.core.base.BaseViewState


data class SignUpEmailState(
    val email: String = "",
    val password: String = ""
): BaseViewState()
