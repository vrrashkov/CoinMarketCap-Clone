package com.vrashkov.coinmarketcapclone.ui.common.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrashkov.coinmarketcapclone.core.enums.ActionButtonOrderEnum
import com.vrashkov.coinmarketcapclone.core.enums.MarketFilterSortByEnum
import com.vrashkov.coinmarketcapclone.core.model.OptionsDialogItem
import com.vrashkov.coinmarketcapclone.core.theme.CoinMarketCapCloneTheme
import com.vrashkov.coinmarketcapclone.core.theme.MarketShapes.ActionButtonRoundShape
import com.vrashkov.coinmarketcapclone.ui.common.pureClickable

@Composable
fun TextOrderActionButton(
    label: String = "",
    textColor: Color = CoinMarketCapCloneTheme.colors.tabNormal,
    orderIconColor: Color = CoinMarketCapCloneTheme.colors.tabSelected,
    onClick: () -> Unit,
    onOrderClick: (ActionButtonOrderEnum) -> Unit,
    defaultOrder: ActionButtonOrderEnum,
    isButtonEnabled: Boolean = true
) {
    val orderAscPainter = rememberVectorPainter(image = Icons.Rounded.ArrowUpward)
    val orderDescPainter = rememberVectorPainter(image = Icons.Rounded.ArrowDownward)
    val currentOrder = remember { mutableStateOf(defaultOrder)}
    var orderContentDescription = ""
    val currentOrderPainter = when (currentOrder.value) {
        ActionButtonOrderEnum.Asc -> {
            orderContentDescription = "order asc"
            orderAscPainter
        }
        ActionButtonOrderEnum.Desc -> {
            orderContentDescription = "order desc"
            orderDescPainter
        }
    }

    var textValueModifier = Modifier.padding(start = 8.dp)
    var sortValueModifier = Modifier.padding(horizontal = 6.dp)

    if (isButtonEnabled) {
        textValueModifier = textValueModifier.pureClickable {
            onClick()
        }

        sortValueModifier = sortValueModifier.pureClickable {
            currentOrder.value = when (currentOrder.value) {
                ActionButtonOrderEnum.Asc -> {
                    ActionButtonOrderEnum.Desc
                }
                ActionButtonOrderEnum.Desc -> {
                    ActionButtonOrderEnum.Asc
                }
            }

            onOrderClick(currentOrder.value)
        }
    }


    ActionButton(
        isButtonEnabled = isButtonEnabled,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    modifier = textValueModifier,
                    text = label,
                    style = CoinMarketCapCloneTheme.typography.display_medium.copy(
                        color = textColor,
                        fontSize = 11.sp,
                    )
                )
                Box(modifier = sortValueModifier) {
                    Image(
                        modifier = Modifier.size(10.dp),
                        painter = currentOrderPainter,
                        contentDescription = orderContentDescription,
                        colorFilter = ColorFilter.tint(orderIconColor)
                    )
                }
            }
        }
    )
}
@Composable
fun TextActionButton(
    label: String = "",
    textColor: Color = CoinMarketCapCloneTheme.colors.tabNormal,
    onClick: () -> Unit,
    isButtonEnabled: Boolean = true
) {

    ActionButton(
        isButtonEnabled = isButtonEnabled,
        onClick = if (isButtonEnabled) onClick else null,
        content = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = label,
                style = CoinMarketCapCloneTheme.typography.display_medium.copy(
                    color = textColor,
                    fontSize = 11.sp,
                )
            )
        }
    )
}
@Composable
fun SingleImageActionButton(
    modifier: Modifier = Modifier.size(18.dp),
    icon: ImageVector,
    contentDescription: String = "",
    iconColor: Color = CoinMarketCapCloneTheme.colors.tabNormal,
    onClick: () -> Unit,
    isButtonEnabled: Boolean = true
) {

    val iconPainter = rememberVectorPainter(image = icon)

    ActionButton(
        isButtonEnabled = isButtonEnabled,
        onClick = if (isButtonEnabled) onClick else null,
        isSingleImage = true,
        content = {
            Image(
                modifier = modifier,
                painter = iconPainter,
                contentDescription = contentDescription,
                colorFilter = ColorFilter.tint(iconColor)
            )
        }
    )
}
@Composable
private fun ActionButton(
    onClick: (() -> Unit)? = null,
    isSingleImage: Boolean = false,
    content: @Composable (() -> Unit)? = null,
    isButtonEnabled: Boolean = true
) {
    val shape: Shape = if (isSingleImage) CircleShape else ActionButtonRoundShape.small
    var modifier = if (isSingleImage) {
        Modifier.size(24.dp)
    } else {
        Modifier.height(24.dp)
    }

    // we do this to prevent stacking multiple onClick events from parent wrappers
    onClick?.let {
        modifier = modifier.pureClickable {
            onClick()
        }
    }

    if (isButtonEnabled) {
        modifier = modifier.background(
            color = CoinMarketCapCloneTheme.colors.content,
            shape = shape
        )
    } else {
        modifier = modifier.background(
            color = CoinMarketCapCloneTheme.colors.content,
            shape = shape
        ).alpha(0.4f)
    }

    Row(modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        content?.invoke()
    }
}

@Preview
@Composable
private fun TextOrderActionButtonPreview() {
    TextOrderActionButton(
        label = "Sort By %",
        onClick = {

        },
        defaultOrder = ActionButtonOrderEnum.Asc,
        onOrderClick = {

        }
    )
}
@Preview
@Composable
private fun TextActionButtonPreview() {
    TextActionButton(
        label = "My Wishlists",
        onClick = {

        }
    )
}

@Preview
@Composable
private fun SingleImageActionButtonPreview() {
    SingleImageActionButton(
        icon = Icons.Rounded.StarBorder,
        onClick = {

        }
    )
}
