package com.romeo.core.data.repository

import com.romeo.core.domain.entity.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getAll(
        page: Int,
        pageSize: Int,
        update: Boolean = true
    ): Flow<List<Character>>

    suspend fun getFavorites(): Flow<List<Character>>
    suspend fun getOne(id: Int): Flow<Character?>
}