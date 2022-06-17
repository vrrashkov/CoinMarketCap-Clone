package com.vrashkov.coinmarketcapclone.di

import android.content.Context
import com.vrashkov.coinmarketcapclone.BuildConfig
import com.vrashkov.coinmarketcapclone.core.util.DataStoreManager
import com.vrashkov.coinmarketcapclone.core.util.ReleaseTimberTree
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTree(): Timber.Tree = when {
        BuildConfig.DEBUG -> Timber.DebugTree()
        else -> ReleaseTimberTree()
    }

    @Provides
    @Singleton
    fun provideDataSource(@ApplicationContext appContext: Context): DataStoreManager =
        DataStoreManager(appContext)
}