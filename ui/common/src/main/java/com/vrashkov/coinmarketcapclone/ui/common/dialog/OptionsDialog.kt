package com.vrashkov.coinmarketcapclone.ui.common.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrashkov.coinmarketcapclone.core.enums.MarketFilterSortByEnum
import com.vrashkov.coinmarketcapclone.core.model.OptionsDialogItem
import com.vrashkov.coinmarketcapclone.core.theme.CoinMarketCapCloneTheme
import com.vrashkov.coinmarketcapclone.ui.common.pureClickable

@Composable
fun <T>OptionsDialog(
    title: String = "Test Tile",
    showModalTransitionDialog: Boolean,
    onDismissRequest: () -> Unit = {},
    selectedItem: MarketFilterSortByEnum,
    onSelectItem: (OptionsDialogItem<T>) -> Unit = {},
    optionslist: List<OptionsDialogItem<T>> = listOf()
) {
    if (showModalTransitionDialog) {
        ModalTransitionDialog(onDismissRequest = onDismissRequest) { modalTransitionDialogHelper ->
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.fillMaxWidth().weight(1f).pureClickable {
                    modalTransitionDialogHelper.triggerAnimatedClose()
                })

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp), color = CoinMarketCapCloneTheme.colors.primary),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = title,
                        style = CoinMarketCapCloneTheme.typography.display_heavy.copy(
                            color = CoinMarketCapCloneTheme.colors.tabSelected,
                            fontSize = 16.sp,
                        )
                    )
                    LazyColumn(Modifier.fillMaxWidth().padding(top = 10.dp)){
                        items(items = optionslist) {

                            var boxColor = CoinMarketCapCloneTheme.colors.primary
                            var textColor = CoinMarketCapCloneTheme.colors.tabNormal

                            if (it.data!! == selectedItem) {
                                boxColor = CoinMarketCapCloneTheme.colors.tabSelected
                                textColor = CoinMarketCapCloneTheme.colors.primary
                            }
                            Box(Modifier.fillMaxWidth()
                                .clickable {
                                    onSelectItem(it)
                                    modalTransitionDialogHelper.triggerAnimatedClose()
                                }
                                .background(color = boxColor)) {
                                Text(
                                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp),
                                    text = it.label,
                                    style = CoinMarketCapCloneTheme.typography.display_heavy.copy(
                                        color = textColor,
                                        fontSize = 16.sp,
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}