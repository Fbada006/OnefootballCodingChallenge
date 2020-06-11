package com.onefootball.di.modules

import com.onefootball.ui.MyNewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesMyNewsActivity(): MyNewsActivity
}