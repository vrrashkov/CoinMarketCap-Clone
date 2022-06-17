package com.vrashkov.coinmarketcapclone.ui.profile.settings.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.vrashkov.coinmarketcapclone.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsMainVM
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
): BaseViewModel<SettingsMainState, SettingsMainEvent>(){

    override val viewState: MutableState<SettingsMainState> = mutableStateOf(SettingsMainState())

    override fun onTriggerEvent(event: SettingsMainEvent){
        when (event) {

        }
    }
}


