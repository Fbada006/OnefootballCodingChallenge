package com.onefootball.di.modules

import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun providesGson() = Gson()
}