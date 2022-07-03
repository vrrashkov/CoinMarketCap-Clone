package com.vrashkov.coinmarketcapclone.ui.profile.authentication.signin.email

import com.vrashkov.coinmarketcapclone.core.base.BaseViewEvent

sealed class SignInEmailEvent : BaseViewEvent(){
    object BackButtonClick: SignInEmailEvent()
    object CreateAccountClick: SignInEmailEvent()

    data class EmailTextFieldValueChange(val newValue: String): SignInEmailEvent()
    data class PasswordTextFieldValueChange(val newValue: String): SignInEmailEvent()
}