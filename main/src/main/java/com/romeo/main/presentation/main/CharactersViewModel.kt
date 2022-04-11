package com.romeo.main.presentation.main

import com.romeo.core.data.repository.CharacterRepository
import com.romeo.core.domain.entity.Character
import com.romeo.core.presentation.BaseViewModel
import com.romeo.core.presentation.list.replace
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take

class CharactersViewModel(
    private val charactersRepository: CharacterRepository
) : BaseViewModel<CharactersViewState>() {

    var characters = listOf<Character>()

    override fun onViewInit() {
        update()
    }

    fun update() {
        runAsync {
            charactersRepository.getAll(1, 20).take(1) .collect {
                characters = it
                emitSuccess(CharactersViewState(it))
            }
        }
    }

    fun onItemPressed(itemId: Int) {
        emitMessage("$itemId pressed")
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