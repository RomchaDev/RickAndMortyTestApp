package com.romeo.main.koin

import com.romeo.main.presentation.main.CharactersViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { CharactersViewModel(get()) }
}