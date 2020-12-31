package com.brizz.stockbittest.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brizz.stockbittest.di.ViewModelKey
import com.brizz.stockbittest.di.factory.ViewModelFactory
import com.brizz.stockbittest.feature.main.watchlist.WatchlistViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WatchlistViewModel::class)
    internal abstract fun providesWatchlistViewModel(viewModel: WatchlistViewModel): ViewModel
}