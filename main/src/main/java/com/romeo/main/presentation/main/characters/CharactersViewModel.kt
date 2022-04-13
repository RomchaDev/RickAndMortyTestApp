package com.romeo.main.presentation.main.characters

import com.romeo.core.data.repository.CharacterRepository
import com.romeo.core.data.repository.TokenRepository
import com.romeo.core.domain.entity.Character
import com.romeo.core.presentation.list.replace
import com.romeo.core.presentation.navigation.global_actions.GlobalToCharDirections
import com.romeo.core.presentation.navigation.global_actions.GlobalToLoginDirections
import com.romeo.main.presentation.main.AbstractCharactersViewModel
import com.romeo.main.presentation.main.CharactersViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take

class CharactersViewModel(
    override val charactersRepository: CharacterRepository,
    tokenRepository: TokenRepository,
    loginDirections: GlobalToLoginDirections,
    characterDirections: GlobalToCharDirections
) : AbstractCharactersViewModel(characterDirections, loginDirections, tokenRepository) {

    private var nextPage = 1

    override fun update() {
        runAsync {
            charactersRepository.getAll(nextPage, INITIAL_CAPACITY).take(1).collect {
                val newList = mutableListOf<Character>()
                newList.addAll(characters)
                newList.addAll(it)
                emitSuccess(CharactersViewState(newList))
                characters = newList
            }
        }
    }

    fun onLikePressed(id: Int) {
        val item = characters.find { it.id == id }
        item?.let {
            val newItem = item.copy(isFavorite = !item.isFavorite)
            val newList = characters.replace(newItem) { it.id == item.id }

            emitSuccess(CharactersViewState(newList))
            characters = newList.toMutableList()

            runAsync {
                if (item.isFavorite) charactersRepository.removeFromFavorites(item.copy())
                else charactersRepository.addToFavorites(item.copy())
            }
        }
    }

    override fun onBindListItem(pos: Int) {
        val curFactor = pos.toFloat() / characters.size.toFloat()
        if (curFactor >= LOAD_FACTOR) {
            nextPage++
            update()
        }
    }

    companion object {
        private const val LOAD_FACTOR = 0.5
        private const val INITIAL_CAPACITY = 10
    }
}