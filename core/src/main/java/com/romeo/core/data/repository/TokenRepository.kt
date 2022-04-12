package com.romeo.core.data.repository

import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    fun get(): Flow<String?>
    suspend fun put(token: String)
    suspend fun remove()
}