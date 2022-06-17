package com.vrashkov.coinmarketcapclone.ui.common.layout


import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension
import com.vrashkov.coinmarketcapclone.core.theme.CoinMarketCapCloneTheme

@Composable
fun NormalScreen(
    modifier: Modifier = Modifier,
    screenModifier: Modifier = Modifier.verticalScroll(rememberScrollState()),

    // Used for small close button and content put on the top right corner.
    topEndContent: @Composable (RowScope.() -> Unit)? = null,

    // Used for the main content with top vertical alignment.
    mainContent: @Composable (ColumnScope.() -> Unit)? = null,

    // Used for the content with bottom vertical alignment.
    bottomContent: @Composable (ColumnScope.() -> Unit)? = null,
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier.pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
            .background(CoinMarketCapCloneTheme.colors.primary)
            .padding(horizontal = 15.dp, vertical = 15.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            topEndContent?.invoke(this)
        }

        ConstraintLayout (
            modifier = Modifier
                .fillMaxSize(),

            ) {
            val (mainRef, bottomRef) = createRefs()

            Column(modifier = Modifier
                .padding(bottom = if (bottomContent != null) 12.dp else 0.dp)
                .constrainAs(bottomRef) {
                    bottom.linkTo(parent.bottom)
                }
            ) {
                bottomContent?.invoke(this)
            }

            Column(
                modifier = screenModifier
                    .constrainAs(mainRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(bottomRef.top)
                        height = Dimension.fillToConstraints
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                mainContent?.invoke(this)
            }
        }
    }
}
