package com.romeo.main.presentation.main.favorites

import com.romeo.core.data.repository.CharacterRepository
import com.romeo.core.data.repository.TokenRepository
import com.romeo.core.domain.entity.Character
import com.romeo.core.presentation.navigation.global_actions.GlobalToCharDirections
import com.romeo.core.presentation.navigation.global_actions.GlobalToLoginDirections
import com.romeo.main.presentation.main.AbstractCharactersViewModel
import com.romeo.main.presentation.main.CharactersViewState
import kotlinx.coroutines.flow.collect

class FavoritesViewModel(
    override val charactersRepository: CharacterRepository,
    tokenRepository: TokenRepository,
    loginDirections: GlobalToLoginDirections,
    characterDirections: GlobalToCharDirections
) : AbstractCharactersViewModel(characterDirections, loginDirections, tokenRepository) {

    override fun update() {
        runAsync {
            charactersRepository.getFavorites().collect { new ->
                emitSuccess(CharactersViewState(new))
                characters = new
            }
        }
    }

    fun deleteFromFavorites(pos: Int) {
        val oldItem = characters[pos]
        val newList = mutableListOf<Character>()

        newList.addAll(characters)
        newList.removeAt(pos)

        emitSuccess(CharactersViewState(newList))
        characters = newList
        runAsync {
            charactersRepository.removeFromFavorites(oldItem.copy())
        }
    }
}