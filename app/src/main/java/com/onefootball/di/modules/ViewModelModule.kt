package com.onefootball.di.modules

import androidx.lifecycle.ViewModel
import com.onefootball.di.ViewModelKey
import com.onefootball.ui.main.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**Provide ViewModels to the dependency graph*/
@Module
abstract class ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindCharacterSearchViewModel(characterSearchViewModel: NewsViewModel): ViewModel
}