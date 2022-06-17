package com.vrashkov.coinmarketcapclone.ui.common.buttons

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.vrashkov.coinmarketcapclone.core.base.ButtonState
import com.vrashkov.coinmarketcapclone.core.theme.CoinMarketCapCloneTheme


@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    replaceModifier: Modifier? = null,
    textModifier: Modifier = Modifier,
    text: String,
    state: ButtonState,
    onClick: () -> Unit,
    isActiveState: Boolean = (state == ButtonState.Active),
    backgroundColor: Color = when (state) {
        ButtonState.Active -> CoinMarketCapCloneTheme.colors.content
        ButtonState.Disabled -> CoinMarketCapCloneTheme.colors.content.copy(alpha = .3f)
        ButtonState.Loading -> CoinMarketCapCloneTheme.colors.content.copy(alpha = .65f)
    },
    textColor: Color = CoinMarketCapCloneTheme.colors.contentSecondary

) {
    val isActive: Boolean = isActiveState

    val localModifier = replaceModifier ?: modifier.fillMaxWidth().height(54.dp)
    Button(
        modifier = localModifier,
        enabled = isActive,
        shape = RoundedCornerShape(9.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = backgroundColor
        ),
        onClick = {
            onClick()
        }) {
            Text(
                modifier = textModifier,
                text = text,
                fontSize = 17.sp,
                style = CoinMarketCapCloneTheme.typography.display_heavy,
                color = textColor,
            )
        }
}

@Preview
@Composable
fun FilledButtonWithActiveStatePreview() {
    MaterialTheme {
        FilledButton(
            text = "Continue",
            state = ButtonState.Active,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun FilledButtonWithDisabledStatePreview() {
    MaterialTheme {
        FilledButton(
            text = "Continue",
            state = ButtonState.Disabled,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun FilledButtonWithLoadingStatePreview() {
    MaterialTheme {
        FilledButton(
            text = "Continue",
            state = ButtonState.Loading,
            onClick = {}
        )
    }
}