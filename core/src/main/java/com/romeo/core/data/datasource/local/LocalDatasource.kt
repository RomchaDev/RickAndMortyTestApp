package com.romeo.core.data.datasource.local

import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass

interface LocalDatasource {
    fun <T : Any> get(keyStr: String, kClass: KClass<T>): Flow<T?>
    suspend fun <T> save(keyStr: String, value: T)
}