package com.brizz.stockbittest.di.module

import com.brizz.stockbittest.feature.login.LoginActivity
import com.brizz.stockbittest.feature.main.MainActivity
import com.brizz.stockbittest.feature.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributesSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributesLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}