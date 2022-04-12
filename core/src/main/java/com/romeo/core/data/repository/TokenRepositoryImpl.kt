package com.romeo.core.data.repository

import com.romeo.core.data.datasource.local.LocalDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

class TokenRepositoryImpl(
    private val localDatasource: LocalDatasource,
    private val tokenKey: String
) : TokenRepository {

    var token: String? = null

    override fun get(): Flow<String?> {
        val res = token?.let {
            flowOf(it)
        } ?: localDatasource.get(tokenKey, String::class)

        print(res)

        return res
    }

    override suspend fun put(token: String) {
        this.token = token
        localDatasource.save(tokenKey, token)
    }

    override suspend fun remove() {
        localDatasource.remove(tokenKey)
    }
}