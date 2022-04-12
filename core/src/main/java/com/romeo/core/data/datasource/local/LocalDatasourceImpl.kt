package com.romeo.core.data.datasource.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.reflect.KClass

open class LocalDatasourceImpl(
    private val context: Context
) : LocalDatasource {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "appDataStore")

    override suspend fun <T> save(keyStr: String, value: T) {
        saveString(keyStr, Gson().toJson(value))
    }

    override fun <T : Any> get(keyStr: String, kClass: KClass<T>): Flow<T> =
        getString(keyStr).map {
            Gson().fromJson(it, kClass.java)
        }

    private suspend fun saveString(key: String, value: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    private fun getString(keyStr: String): Flow<String?> {
        val key = stringPreferencesKey(keyStr)

        return context.dataStore.data.map { preferences ->
            preferences[key]
        }
    }

    override suspend fun remove(keyStr: String) {
        context.dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(keyStr))
        }
    }
}