package com.vrashkov.coinmarketcapclone.core.enums

enum class MarketFilterEnum {
    CoinFavourites,
    CoinWishlist,
    CoinCurrency,
    CoinSortBy,
    CoinTime,
    CoinLookingFor,

    ExchangeFavourites,
    ExchangeCurrency,
    ExchangeSortBy,
    ExchangeTime
}

enum class MarketFilterSortByEnum(val label: String) {
    MarketCap("Market Cap"),
    Volume("Volume")
}