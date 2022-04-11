package com.romeo.main.presentation.main

import com.romeo.core.data.repository.CharacterRepository
import com.romeo.core.domain.entity.Character
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

    fun deleteFromFavorites(id: Int) {
        val old = characters.find { it.id == id }

        old?.let {
            val newList = mutableListOf<Character>()
            newList.addAll(characters)
            newList.removeIf { it.id == id }
            emitSuccess(CharactersViewState(newList))
            characters = newList
        }
    }
}