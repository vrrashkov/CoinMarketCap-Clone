package com.vrashkov.coinmarketcapclone.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vrashkov.coinmarketcapclone.datasource.database.dao.CoinsDao
import com.vrashkov.coinmarketcapclone.datasource.database.entity.CoinsEntity

@Database(entities = [CoinsEntity::class], version = 1)
abstract class MarketDatabase: RoomDatabase() {

    abstract fun coinsDb(): CoinsDao

    companion object{
        val DATABASE_NAME: String = "market_db"
    }


}