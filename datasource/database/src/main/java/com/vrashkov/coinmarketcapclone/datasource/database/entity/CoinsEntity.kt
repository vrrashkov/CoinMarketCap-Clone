package com.vrashkov.coinmarketcapclone.datasource.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
class CoinsEntity(

    @PrimaryKey
    @ColumnInfo(name = "symbol_id")
    var symbol_id: String,

    @ColumnInfo(name = "is_favourite")
    var isFavourite: Boolean = false,

)