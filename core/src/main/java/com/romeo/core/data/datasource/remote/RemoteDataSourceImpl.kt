package com.romeo.core.data.datasource.remote

import com.romeo.core.data.api.ApiService
import com.romeo.core.data.api.dto.TokenDTO
import com.romeo.core.domain.entity.Character
import com.romeo.core.domain.entity.SignInEntity
import com.romeo.core.domain.entity.SignUpEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RemoteDataSourceImpl(
    private val apiService: ApiService
) : RemoteDatasource {

    override fun signUp(signUpEntity: SignUpEntity): Flow<TokenDTO> {
        return apiService.signUp(signUpEntity)
    }

    override fun signIn(signInEntity: SignInEntity): Flow<TokenDTO> {
        return apiService.signIn(signInEntity)
    }

    override fun getAllCharacters(): Flow<List<Character>> {
        return apiService.getAllCharacters().map {
            it.map { char -> Character.fromDTO(char) }
        }
    }

    override fun getCharacter(id: Int): Flow<Character> {
        return apiService.getCharacter(id).map { Character.fromDTO(it) }
    }
}