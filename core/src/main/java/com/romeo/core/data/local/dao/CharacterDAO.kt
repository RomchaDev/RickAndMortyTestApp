package com.romeo.core.data.local.dao

import com.romeo.core.domain.entity.Character

interface CharacterDAO : DAO<Int, Character> {
    suspend fun getFavorites(): List<Character>
}