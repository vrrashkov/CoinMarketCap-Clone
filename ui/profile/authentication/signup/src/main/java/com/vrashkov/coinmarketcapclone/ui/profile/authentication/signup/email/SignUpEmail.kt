package com.vrashkov.coinmarketcapclone.ui.profile.authentication.signup.email

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vrashkov.coinmarketcapclone.core.base.ButtonState
import com.vrashkov.coinmarketcapclone.core.enums.TextFieldEnum
import com.vrashkov.coinmarketcapclone.core.navigation.LocalNavigationState
import com.vrashkov.coinmarketcapclone.ui.common.buttons.FilledButton
import com.vrashkov.coinmarketcapclone.ui.common.header.BackButtonHeader
import com.vrashkov.coinmarketcapclone.ui.common.layout.NormalScreen
import com.vrashkov.coinmarketcapclone.ui.common.textfield.PasswordTextField
import com.vrashkov.coinmarketcapclone.ui.common.textfield.PrimaryTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.vrashkov.coinmarketcapclone.core.base.NavigationEvent


@ExperimentalMaterialApi
@Composable
fun SignUpEmailScreen(mainContent: @Composable (ColumnScope.() -> Unit)? = null) {

    val baseAppState = LocalNavigationState.baseNavigation
    val authAppState = LocalNavigationState.authNavigation

    val baseNavController: NavHostController = baseAppState.navController
    val authNavController: NavHostController = authAppState.navController

    val viewModel = hiltViewModel<SignUpEmailVM>()

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(Unit) {
        viewModel.navigationEventFlow.collect {
            when (it) {
                is NavigationEvent.NavigateBack -> {
                    baseAppState.navigateBack()
                }
            }
        }
    }

    val onTriggerSignUpEmailEvents = viewModel::onTriggerEvent
    val viewStateEmail = viewModel.viewState.value

    SignUpEmailScreenLayout(viewStateEmail = viewStateEmail,onTriggerSignUpEmailEvents = onTriggerSignUpEmailEvents)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SignUpEmailScreenLayout(viewStateEmail: SignUpEmailState, onTriggerSignUpEmailEvents: (SignUpEmailEvent) -> Unit) {
    var emailFocusState by remember { mutableStateOf(TextFieldEnum.NotFocused) }
    var passwordFocusState by remember { mutableStateOf(TextFieldEnum.NotFocused) }

    NormalScreen(
        modifier = Modifier.fillMaxSize(),
        topEndContent = {
            BackButtonHeader(
                modifier = Modifier.padding(top = 15.dp),
                title = "Create an Account",
                onBackClick = {
                    onTriggerSignUpEmailEvents(SignUpEmailEvent.BackButtonClick)
                }
            )
        },
        mainContent = {
            PrimaryTextField(
                modifier = Modifier.padding(top = 24.dp),
                state = emailFocusState,
                onFocusChanged = {
                    emailFocusState = when {
                        it.isFocused -> TextFieldEnum.Focused
                        else -> TextFieldEnum.NotFocused
                    }
                },
                label = "Email Address",
                placeholder = "Enter your email address...",
                value = viewStateEmail.email,
                onValueChange = {
                    onTriggerSignUpEmailEvents(SignUpEmailEvent.EmailTextFieldValueChange(it))
                }
            )
            PasswordTextField(
                modifier = Modifier.padding(top = 24.dp),
                focusState = passwordFocusState,
                onFocusChanged = {
                    passwordFocusState = when {
                        it.isFocused -> TextFieldEnum.Focused
                        else -> TextFieldEnum.NotFocused
                    }
                },
                labelSuffixOnClick = {},
                label = "Password",
                placeholder = "Enter password...",
                value = viewStateEmail.password,
                onValueChange = {
                    onTriggerSignUpEmailEvents(SignUpEmailEvent.PasswordTextFieldValueChange(it))
                }
            )
        },
        bottomContent = {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                FilledButton(
                    text = "Create an Account",
                    state = ButtonState.Disabled,
                    onClick = {

                    }
                )
            }
        }
    )
}