package com.romeo.core.data.repository

import com.romeo.core.data.datasource.remote.CharacterRemoteDatasource
import com.romeo.core.data.local.dao.CharacterDAO
import com.romeo.core.domain.entity.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single

class CharacterRepositoryImpl(
    private val remoteDatasource: CharacterRemoteDatasource,
    private val localDatasource: CharacterDAO
) : CharacterRepository {
    private var characters: List<Character>? = null

    override suspend fun getAll(
        page: Int,
        pageSize: Int,
        update: Boolean
    ): Flow<List<Character>> {
        return if (update) {
            try {
                characters = remoteDatasource.getAllCharacters(page, pageSize).single()
                flowOf(characters!!)
            } catch (t: Throwable) {
                flowOfLocals()
            }
        } else flowOfLocals()
    }

    private suspend fun flowOfLocals() = flowOf(
        characters ?: localDatasource.getAll().apply { characters = this }
    )

    private suspend fun flowOfFavorites() = flowOf(localDatasource.getFavorites())

    override suspend fun getFavorites(): Flow<List<Character>> {
        return flowOfFavorites()
    }

    override suspend fun getOne(id: Int): Flow<Character?> {
        return flowOf(localDatasource.get(id))
    }
}