package com.onefootball.di

import android.content.Context
import com.onefootball.MyNewsApplication
import com.onefootball.di.modules.ActivityBuildersModule
import com.onefootball.di.modules.AppModule
import com.onefootball.di.modules.ViewModelFactoryModule
import com.onefootball.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        ActivityBuildersModule::class,
        AppModule::class
    ]
)
interface AppComponent {

    fun inject(application: MyNewsApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}