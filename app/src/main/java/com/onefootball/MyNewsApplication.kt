package com.onefootball

import android.app.Application
import androidx.preference.PreferenceManager
import com.onefootball.di.DaggerAppComponent
import com.onefootball.utils.ThemeHelper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
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

        initTimber()
        setNightMode()
    }

    // We only want to log for debug builds only
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setNightMode() {
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val themePref = sharedPreferences.getString(
            getString(R.string.pref_night_mode_key),
            ThemeHelper.DEFAULT_MODE
        )
        ThemeHelper.applyTheme(themePref)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}