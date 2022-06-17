package com.vrashkov.coinmarketcapclone.ui.profile.settings.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vrashkov.coinmarketcapclone.core.actions.DashboardTabBarActions
import com.vrashkov.coinmarketcapclone.core.base.ButtonState
import com.vrashkov.coinmarketcapclone.core.base.NavigationEvent
import com.vrashkov.coinmarketcapclone.core.enums.TextFieldEnum
import com.vrashkov.coinmarketcapclone.core.navigation.*
import com.vrashkov.coinmarketcapclone.core.theme.CoinMarketCapCloneTheme
import com.vrashkov.coinmarketcapclone.ui.common.buttons.FilledButton
import com.vrashkov.coinmarketcapclone.ui.common.header.BackButtonHeader
import com.vrashkov.coinmarketcapclone.ui.common.layout.NormalScreen
import com.vrashkov.coinmarketcapclone.ui.common.pureClickable
import com.vrashkov.coinmarketcapclone.ui.common.rippleClickable
import com.vrashkov.coinmarketcapclone.ui.common.textfield.PasswordTextField
import com.vrashkov.coinmarketcapclone.ui.common.textfield.PrimaryTextField


@ExperimentalMaterialApi
@Composable
fun SettingsMainScreen(mainContent: @Composable (ColumnScope.() -> Unit)? = null) {

    val baseAppState = LocalNavigationState.baseNavigation
    val settingsAppState = LocalNavigationState.settingsNavigation

    val baseNavController: NavHostController = baseAppState.navController
    val settingsNavController: NavHostController = settingsAppState.navController

    val viewModel = hiltViewModel<SettingsMainVM>()

    LaunchedEffect(Unit) {
        viewModel.navigationEventFlow.collect {
            when (it) {

            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.actionsEventFlow.collect {
            when (it) {

            }
        }
    }

    val onTriggerEvents = viewModel::onTriggerEvent

    SettingsMainScreenLayout(
        mainContent = mainContent,
        onTriggerEvents = onTriggerEvents
    )

}
@Composable
private fun SettingsMainScreenLayout(
    mainContent: @Composable (ColumnScope.() -> Unit)? = null,
    onTriggerEvents: (SettingsMainEvent) -> Unit,
) {

    NormalScreen(
        modifier = Modifier.fillMaxSize(),
        topEndContent = {
            BackButtonHeader(
                modifier = Modifier.padding(top = 15.dp),
                title = "Settings",
                onBackClick = {

                }
            )
        },
        mainContent = {

        }
    )
}
