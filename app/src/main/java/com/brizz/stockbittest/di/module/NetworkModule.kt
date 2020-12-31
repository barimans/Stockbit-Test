package com.brizz.stockbittest.di.module

import com.brizz.stockbittest.data.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(): RemoteRepository = RemoteRepository.getInstance()

}