package com.onefootball.di

import android.content.Context
import com.onefootball.MyNewsApplication
import com.onefootball.di.modules.ActivityBuildersModule
import com.onefootball.di.modules.ViewModelFactoryModule
import com.onefootball.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Core Application Dagger Component
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        ActivityBuildersModule::class
    ]
)
interface AppComponent {

    fun inject(application: MyNewsApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}