package com.romeo.core.data.datasource.remote

import com.romeo.core.domain.entity.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRemoteDatasource {

    fun getAllCharacters(
        page: Int,
        pageSize: Int
    ): Flow<List<Character>>

    fun getCharacter(id: Int): Flow<Character>
}