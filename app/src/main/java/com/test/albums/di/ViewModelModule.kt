package com.test.albums.di

import com.test.albums.viewmodels.AlbumsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AlbumsListViewModel(get()) }
}