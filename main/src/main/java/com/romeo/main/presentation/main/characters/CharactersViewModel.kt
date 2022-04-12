package com.romeo.main.presentation.main.characters

import com.romeo.core.data.repository.CharacterRepository
import com.romeo.core.presentation.list.replace
import com.romeo.main.presentation.main.AbstractCharactersViewModel
import com.romeo.main.presentation.main.CharactersViewState
import com.romeo.main.presentation.main.navigation.CharactersToCharDirections
import com.romeo.main.presentation.main.navigation.GlobalToCharDirections
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.take

class CharactersViewModel(
    override val charactersRepository: CharacterRepository,
    characterDirections: CharactersToCharDirections
) : AbstractCharactersViewModel(characterDirections) {

    override fun update() {
        runAsync {
            charactersRepository.getAll(1, 20).take(1) .collect {
                characters = it
                emitSuccess(CharactersViewState(it))
            }
        }
    }

    fun onLikePressed(id: Int) {
        val item = characters.find { it.id == id }
        item?.let {
            val newItem = item.copy(isFavorite = !item.isFavorite)
            val newList = characters.replace(newItem) { it.id == item.id }

            emitSuccess(CharactersViewState(newList))
            characters = newList

            runAsync {
                if (item.isFavorite) charactersRepository.removeFromFavorites(item.copy())
                else charactersRepository.addToFavorites(item.copy())
            }
        }
    }
}