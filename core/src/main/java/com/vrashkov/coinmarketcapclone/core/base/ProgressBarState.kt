package com.vrashkov.coinmarketcapclone.core.base

sealed class ProgressBarState{
    
    object Loading: ProgressBarState()
    
    object Gone: ProgressBarState()
}