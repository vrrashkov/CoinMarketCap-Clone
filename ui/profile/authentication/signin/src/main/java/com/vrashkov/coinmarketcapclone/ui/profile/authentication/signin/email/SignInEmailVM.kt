package com.vrashkov.coinmarketcapclone.ui.profile.authentication.signin.email

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.vrashkov.coinmarketcapclone.core.base.BaseViewModel
import com.vrashkov.coinmarketcapclone.core.base.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInEmailVM
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
): BaseViewModel<SignInEmailState, SignInEmailEvent>(){

    override val viewState: MutableState<SignInEmailState> = mutableStateOf(SignInEmailState())

    override fun onTriggerEvent(event: SignInEmailEvent){
        when (event) {
            is SignInEmailEvent.EmailTextFieldValueChange -> viewState.value = viewState.value.copy(
                email = event.newValue,
            )
            is SignInEmailEvent.PasswordTextFieldValueChange -> viewState.value = viewState.value.copy(
                password = event.newValue,
            )
            SignInEmailEvent.BackButtonClick -> viewModelScope.launch {
                _navigationEventFlow.emit(NavigationEvent.NavigateBack)
            }
        }
    }
}


