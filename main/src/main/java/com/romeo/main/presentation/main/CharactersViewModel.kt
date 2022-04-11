package com.romeo.main.presentation.main

import com.romeo.core.data.repository.CharacterRepository
import com.romeo.core.presentation.BaseViewModel
import kotlinx.coroutines.flow.collect

class CharactersViewModel(
    private val charactersRepository: CharacterRepository
) : BaseViewModel<CharactersViewState>() {

    override fun onViewInit() {
        update()
    }

    fun update() {
        runAsync {
            charactersRepository.getAll(1, 20).collect {
                emitSuccess(CharactersViewState(it))
            }
        }
    }

    fun onItemPressed(itemId: Int) {
        emitMessage("$itemId pressed")
    }
}