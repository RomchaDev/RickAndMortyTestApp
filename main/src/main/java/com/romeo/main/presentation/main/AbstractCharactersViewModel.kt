package com.romeo.main.presentation.main

import com.romeo.core.data.repository.CharacterRepository
import com.romeo.core.domain.entity.Character
import com.romeo.core.presentation.BaseViewModel
import com.romeo.core.presentation.navigation.CHAR_KEY
import com.romeo.main.presentation.main.navigation.CharacterDirections
import kotlinx.coroutines.flow.collect

abstract class AbstractCharactersViewModel(
    private val characterDirections: CharacterDirections
) : BaseViewModel<CharactersViewState>() {

    protected abstract val charactersRepository: CharacterRepository

    protected var characters = listOf<Character>()

    override fun onViewInit() {
        update()
    }

    fun onItemPressed(itemId: Int) {
        runAsync {
            charactersRepository.getOne(itemId).collect {
                characterDirections.arguments.putParcelable(CHAR_KEY, it)
                navigateTo(characterDirections)
            }
        }
    }

    abstract fun update()
}