package com.brizz.stockbittest.di.module

import android.app.Application
import android.content.Context
import com.brizz.stockbittest.StockbitApp
import com.brizz.stockbittest.data.remote.RemoteRepository
import com.brizz.stockbittest.data.repository.impl.WatchlistRepositoryImpl
import com.brizz.stockbittest.feature.main.watchlist.pagination.WatchlistDataSource
import com.brizz.stockbittest.feature.main.watchlist.pagination.WatchlistDataSourceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(app: StockbitApp): Context = app

    @Provides
    @Singleton
    fun provideApplication(app: StockbitApp): Application = app

    @Provides
    @Singleton
    fun providesDetailRepository(remote: RemoteRepository) = WatchlistRepositoryImpl(remote)

    @Provides
    @Singleton
    fun providesWatchlistDataSource(repositoryImpl: WatchlistRepositoryImpl) = WatchlistDataSource(repositoryImpl)

    @Provides
    @Singleton
    fun providesWatchlistDataSourceFactory(repositoryImpl: WatchlistRepositoryImpl) = WatchlistDataSourceFactory(repositoryImpl)

}