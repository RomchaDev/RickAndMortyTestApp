package com.romeo.character.koin

import com.romeo.character.presentation.CharacterViewModel
import com.romeo.core.domain.entity.Character
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterModule = module {
    viewModel { (char: Character?) -> CharacterViewModel(get(), char) }
}