package com.onefootball

import android.app.Application
import com.onefootball.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyNewsApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .factory()
            .create(applicationContext)
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}