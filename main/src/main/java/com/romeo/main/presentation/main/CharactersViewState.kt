package com.romeo.main.presentation.main

import com.romeo.core.domain.entity.AppStateEntity
import com.romeo.core.domain.entity.Character

data class CharactersViewState(
    val characters: List<Character>
) : AppStateEntity