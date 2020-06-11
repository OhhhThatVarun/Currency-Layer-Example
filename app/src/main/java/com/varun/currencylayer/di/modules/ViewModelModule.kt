package com.varun.currencylayer.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.varun.currencylayer.di.annotations.ViewModelKey
import com.varun.currencylayer.ui.ViewModelFactory
import com.varun.currencylayer.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}