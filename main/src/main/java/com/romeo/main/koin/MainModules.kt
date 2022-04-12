package com.romeo.main.koin

import com.romeo.main.presentation.main.characters.CharactersViewModel
import com.romeo.main.presentation.main.favorites.FavoritesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { CharactersViewModel(get(), get(), get(), get()) }
    viewModel { FavoritesViewModel(get(), get(), get(), get()) }
}