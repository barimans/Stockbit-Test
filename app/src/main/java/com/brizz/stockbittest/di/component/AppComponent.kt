package com.brizz.stockbittest.di.component

import com.brizz.stockbittest.StockbitApp
import com.brizz.stockbittest.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBuilder::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        FragmentModule::class
    ]
)

interface AppComponent : AndroidInjector<StockbitApp>{

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: StockbitApp): Builder

        fun build(): AppComponent
    }
}