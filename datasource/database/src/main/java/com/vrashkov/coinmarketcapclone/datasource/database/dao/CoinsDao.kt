package com.vrashkov.coinmarketcapclone.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vrashkov.coinmarketcapclone.datasource.database.entity.CoinsEntity

@Dao
interface CoinsDao {

    @Query("SELECT * FROM coins WHERE symbol_id = :symbolId ")
    suspend fun get(symbolId: String): CoinsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coinsEntity: CoinsEntity)

    @Query("UPDATE coins SET is_favourite = :isFavourite WHERE symbol_id = :symbolId ")
    suspend fun updateFavouriteCoin(isFavourite: Boolean, symbolId: String) : Int

}