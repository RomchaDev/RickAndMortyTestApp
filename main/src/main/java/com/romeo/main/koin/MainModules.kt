package com.romeo.main.koin

import com.romeo.main.presentation.main.characters.CharactersViewModel
import com.romeo.main.presentation.main.favorites.FavoritesViewModel
import com.romeo.main.presentation.main.navigation.CharactersToCharDirections
import com.romeo.main.presentation.main.navigation.FavoritesToCharDirections
import com.romeo.main.presentation.main.navigation.GlobalToCharDirections
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { CharactersViewModel(get(), get()) }
    viewModel { FavoritesViewModel(get(), get()) }

    factory { FavoritesToCharDirections() }
    factory { CharactersToCharDirections() }
    factory { GlobalToCharDirections() }
}