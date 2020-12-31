package com.brizz.stockbittest.di.module

import com.brizz.stockbittest.feature.main.watchlist.WatchlistFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): WatchlistFragment
}