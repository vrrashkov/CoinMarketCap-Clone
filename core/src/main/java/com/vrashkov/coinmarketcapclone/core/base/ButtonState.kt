package com.vrashkov.coinmarketcapclone.core.base

sealed class ButtonState {

    object Active: ButtonState()

    object Disabled: ButtonState()

    object Loading: ButtonState()

}