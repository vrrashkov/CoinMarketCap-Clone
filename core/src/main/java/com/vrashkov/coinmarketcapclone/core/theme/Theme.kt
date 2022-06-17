package com.vrashkov.coinmarketcapclone.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.parseConstraintSets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vrashkov.coinmarketcapclone.core.theme.CoinMarketCapCloneTheme.typography

fun lightColors(): AppColors = AppColors(
    primary = MarketColors.white,
    secondary = MarketColors.black,

    tabNormal = MarketColors.gray_7,
    tabSelected = MarketColors.deep_lilac,

    content = MarketColors.gray_4,
    contentDimmed = MarketColors.gray_5,
    contentSecondary = MarketColors.gray_6,

    aboveContent = MarketColors.gray_7,

    correct = MarketColors.green,
    error = MarketColors.red,
    aboveContentDeeper = MarketColors.gray_11

)
fun darkColors(): AppColors = AppColors(
    primary = MarketColors.black,
    secondary = MarketColors.white,

    tabNormal = MarketColors.gray_5,
    tabSelected = MarketColors.deep_lilac,

    content = MarketColors.gray_4,
    contentDimmed = MarketColors.gray_5,
    contentSecondary = MarketColors.gray_6,

    aboveContent = MarketColors.gray_5,
    aboveContentDeeper = MarketColors.gray_1,

    correct = MarketColors.green,
    error = MarketColors.red,
)

internal val localColors = staticCompositionLocalOf { lightColors() }

object CoinMarketCapCloneTheme {

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = localColors.current


    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

}

@Composable
fun CoinMarketCapCloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(if (darkTheme) darkColors().primary else lightColors().primary)
    systemUiController.isStatusBarVisible = true
    systemUiController.statusBarDarkContentEnabled = !darkTheme
    systemUiController.setNavigationBarColor(if (darkTheme) darkColors().primary else lightColors().primary)
    systemUiController.navigationBarDarkContentEnabled = !darkTheme
    //systemUiController.setSystemBarsColor(color = Color.Transparent)
    val rememberedColors = remember { if (darkTheme) darkColors() else lightColors() }

    CompositionLocalProvider(
        localColors provides rememberedColors,
        LocalTypography provides typography
    ) {
        // Set the default typography
        ProvideTextStyle(value = typography.display_regular, content = content)
    }
}