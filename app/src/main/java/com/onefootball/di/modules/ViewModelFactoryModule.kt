package com.onefootball.di.modules

import androidx.lifecycle.ViewModelProvider
import com.onefootball.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

/**Provide the Factory to the graph*/
@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}