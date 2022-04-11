package com.romeo.core.data.local.dao

interface DAO<K, T> {
    suspend fun getAll(): List<T>
    suspend fun putAll(data: List<T>)
    suspend fun put(data: T)
    suspend fun get(id: K): T?
}