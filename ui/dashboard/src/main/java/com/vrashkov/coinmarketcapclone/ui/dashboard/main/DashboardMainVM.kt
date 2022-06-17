package com.vrashkov.coinmarketcapclone.ui.dashboard.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.vrashkov.coinmarketcapclone.core.base.BaseViewModel
import com.vrashkov.coinmarketcapclone.ui.dashboard.glucoses.DashboardMainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardMainVM
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
): BaseViewModel<DashboardMainState, DashboardMainEvent>(){

    override val viewState: MutableState<DashboardMainState> = mutableStateOf(DashboardMainState())

    override fun onTriggerEvent(event: DashboardMainEvent){
        when (event) {
            is DashboardMainEvent.MarketClick -> viewModelScope.launch {
                _navigationEventFlow.emit(DashboardMainNavigationEvent.NavigateToMarketTab)
            }
            is DashboardMainEvent.ExploreClick -> viewModelScope.launch {
                _navigationEventFlow.emit(DashboardMainNavigationEvent.NavigateToExploreTab)
            }
            is DashboardMainEvent.PortfolioClick -> viewModelScope.launch {
                _navigationEventFlow.emit(DashboardMainNavigationEvent.NavigateToPortfolioTab)
            }
            is DashboardMainEvent.GravityClick -> viewModelScope.launch {
                _navigationEventFlow.emit(DashboardMainNavigationEvent.NavigateToGravityTab)
            }

            is DashboardMainEvent.OpenDrawer -> viewModelScope.launch {
                _actionsEventFlow.emit(DashboardMainActionEvent.OpenDrawer)
            }

            is DashboardMainEvent.LoginClick -> viewModelScope.launch {
                _navigationEventFlow.emit(DashboardMainNavigationEvent.NavigateToLogin)
            }
            is DashboardMainEvent.RegisterClick -> viewModelScope.launch {
                _navigationEventFlow.emit(DashboardMainNavigationEvent.NavigateToRegister)
            }
        }
    }
}


