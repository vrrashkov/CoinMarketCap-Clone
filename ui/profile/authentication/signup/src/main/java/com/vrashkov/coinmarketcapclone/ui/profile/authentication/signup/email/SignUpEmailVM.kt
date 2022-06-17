package com.vrashkov.coinmarketcapclone.ui.profile.authentication.signup.email

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
class SignUpEmailVM
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
): BaseViewModel<SignUpEmailState, SignUpEmailEvent>(){

    override val viewState: MutableState<SignUpEmailState> = mutableStateOf(SignUpEmailState())

    override fun onTriggerEvent(event: SignUpEmailEvent){
        when (event) {
            is SignUpEmailEvent.EmailTextFieldValueChange -> viewState.value = viewState.value.copy(
                email = event.newValue,
            )
            is SignUpEmailEvent.PasswordTextFieldValueChange -> viewState.value = viewState.value.copy(
                password = event.newValue,
            )
            SignUpEmailEvent.BackButtonClick -> viewModelScope.launch {
                _navigationEventFlow.emit(NavigationEvent.NavigateBack)
            }
        }
    }
}


