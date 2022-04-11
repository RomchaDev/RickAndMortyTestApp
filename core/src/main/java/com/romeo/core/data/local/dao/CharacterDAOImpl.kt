package com.romeo.core.data.local.dao

import com.romeo.core.data.local.entity.CharacterLocal
import com.romeo.core.domain.entity.Character
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.CoroutineDispatcher

class CharacterDAOImpl(
    private val realm: Realm,
    private val dispatcher: CoroutineDispatcher
) : CharacterDAO {

    override suspend fun getFavorites(): List<Character> {
        val res = mutableListOf<Character>()

        realm.executeTransactionAwait { transaction ->
            res.addAll(
                transaction
                    .where(CharacterLocal::class.java)
                    .equalTo("isFavorite", true)
                    .findAll()
                    .map { Character.fromLocal(it) }
            )
        }

        return res
    }

    override suspend fun get(id: Int): Character? {
        var char: CharacterLocal? = null

        realm.executeTransactionAwait { transaction ->
            char = transaction
                .where(CharacterLocal::class.java)
                .equalTo("isFavorite", true)
                .findFirst()
        }

        return char?.let { Character.fromLocal(it) }
    }

    override suspend fun getAll(): List<Character> {
        val res = mutableListOf<Character>()

        realm.executeTransactionAwait { transaction ->
            res.addAll(
                transaction
                    .where(CharacterLocal::class.java)
                    .findAll()
                    .map { Character.fromLocal(it) }
            )
        }

        return res
    }

    override suspend fun putAll(data: List<Character>) {
        val dataLocal = data.map { CharacterLocal.fromDomain(it) }

        realm.executeTransactionAwait(dispatcher) { transaction ->
            transaction.insert(dataLocal)
        }
    }

    override suspend fun put(data: Character) {
        realm.executeTransactionAwait(dispatcher) { transaction ->
            transaction.insert(CharacterLocal.fromDomain(data))
        }
    }
}