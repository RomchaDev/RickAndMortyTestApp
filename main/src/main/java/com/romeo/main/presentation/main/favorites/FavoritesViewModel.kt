package com.romeo.main.presentation.main.favorites

import com.romeo.core.data.repository.CharacterRepository
import com.romeo.core.domain.entity.Character
import com.romeo.main.presentation.main.AbstractCharactersViewModel
import com.romeo.main.presentation.main.CharactersViewState
import kotlinx.coroutines.flow.collect

class FavoritesViewModel(
    private val repository: CharacterRepository
) : AbstractCharactersViewModel() {

    override fun update() {
        runAsync {
            repository.getFavorites().collect { new ->
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
            repository.removeFromFavorites(oldItem.copy())
        }
    }
}