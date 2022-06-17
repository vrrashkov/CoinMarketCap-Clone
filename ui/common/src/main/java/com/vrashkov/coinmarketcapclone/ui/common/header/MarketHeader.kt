package com.vrashkov.coinmarketcapclone.ui.common.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vrashkov.coinmarketcapclone.core.theme.CoinMarketCapCloneTheme

@Composable
fun MarketHeader(
    onProfileClick: () -> Unit,
    onCartClick: () -> Unit,
    onSearchClick: () -> Unit,
    centerContent: @Composable (() -> Unit)? = null,
    imageUrl: String? = "https://s2.coinmarketcap.com/static/img/coins/64x64/1839.png"
) {
    val accountImagePlaceholder = rememberVectorPainter(image = Icons.Rounded.AccountCircle)
    val cart = rememberVectorPainter(image = Icons.Rounded.ShoppingCart)
    val search = rememberVectorPainter(image = Icons.Rounded.Search)

    ScreenHeader(
        modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
        leftContent = {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = accountImagePlaceholder,
                contentDescription = "profile image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(30.dp).clip(CircleShape).clickable {
                    onProfileClick()
                }
            )
        },
        centerContent = {
            centerContent?.invoke()
        },
        rightContent = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp))
            {
                Image(
                    modifier = Modifier.size(24.dp).clickable {
                        onCartClick()
                    },
                    painter = cart,
                    contentDescription = "shopping cart",
                    colorFilter = ColorFilter.tint(CoinMarketCapCloneTheme.colors.tabSelected)
                )

                Image(
                    modifier = Modifier.size(24.dp).clickable {
                        onSearchClick()
                    },
                    painter = search,
                    contentDescription = "search icon",
                    colorFilter = ColorFilter.tint(CoinMarketCapCloneTheme.colors.tabNormal)
                )
            }
        }
    )
}