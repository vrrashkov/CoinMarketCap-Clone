package com.vrashkov.coinmarketcapclone.ui.profile.authentication.signup.email

import com.vrashkov.coinmarketcapclone.core.base.BaseViewEvent

sealed class SignUpEmailEvent : BaseViewEvent(){
    object BackButtonClick: SignUpEmailEvent()

    data class EmailTextFieldValueChange(val newValue: String): SignUpEmailEvent()
    data class PasswordTextFieldValueChange(val newValue: String): SignUpEmailEvent()
}