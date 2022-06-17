package com.vrashkov.coinmarketcapclone.di

import android.content.Context
import androidx.room.Room
import com.vrashkov.coinmarketcapclone.datasource.database.MarketDatabase
import com.vrashkov.coinmarketcapclone.datasource.database.dao.CoinsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): MarketDatabase {
        return Room
            .databaseBuilder(
                context,
                MarketDatabase::class.java,
                MarketDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCoinsDAO(coinsDatabase: MarketDatabase): CoinsDao {
        return coinsDatabase.coinsDb()
    }

}














