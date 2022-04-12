package com.romeo.character.presentation

import com.romeo.core.domain.entity.AppStateEntity
import com.romeo.core.domain.entity.Character

sealed class CharacterViewState : AppStateEntity {
    data class CharacterState(val character: Character) : CharacterViewState()
    object ToAddToFav : CharacterViewState()
    object ToRemoveFromFav : CharacterViewState()
}