package com.romeo.core.data.datasource.remote

import com.romeo.core.data.api.dto.TokenDTO
import com.romeo.core.domain.entity.Character
import com.romeo.core.domain.entity.SignInEntity
import com.romeo.core.domain.entity.SignUpEntity
import kotlinx.coroutines.flow.Flow

interface RemoteDatasource {

    fun signUp(signUpEntity: SignUpEntity): Flow<TokenDTO>

    fun signIn(signInEntity: SignInEntity): Flow<TokenDTO>

    fun getAllCharacters(): Flow<List<Character>>

    fun getCharacter(id: Int): Flow<Character>
}