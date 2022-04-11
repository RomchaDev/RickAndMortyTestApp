package com.romeo.main.presentation.main

import com.romeo.core.domain.entity.Character
import com.romeo.core.presentation.BaseViewModel

abstract class AbstractCharactersViewModel : BaseViewModel<CharactersViewState>() {

    protected var characters = listOf<Character>()

    override fun onViewInit() {
        update()
    }

    fun onItemPressed(itemId: Int) {
        emitMessage(itemId.toString())
    }

    abstract fun update()
}