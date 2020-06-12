package com.onefootball.di.modules

import com.onefootball.ui.main.MyNewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**This module generates Android Injectors for the Activities listed here
 * The activities have to call AndroidInjection.inject(this) before
 * calling the super implementation of onCreate()
 * */
@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesMyNewsActivity(): MyNewsActivity
}