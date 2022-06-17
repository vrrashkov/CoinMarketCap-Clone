package com.vrashkov.coinmarketcapclone.di

import android.app.Application
import coil.ImageLoader
import coil.memory.MemoryCache
import com.vrashkov.coinmarketcapclone.core.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoilModule {

    @Provides
    @Singleton
    fun provideImageLoader(app: Application): ImageLoader{
        return ImageLoader.Builder(app)
            .error(R.drawable.error_image)
            .placeholder(R.drawable.white_background)
            //.memoryCache { MemoryCache.Builder(app).maxSizePercent(0.25).build() }
            .crossfade(true)
            .build()
    }
}














