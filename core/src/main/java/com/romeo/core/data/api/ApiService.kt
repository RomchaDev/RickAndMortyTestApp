package com.romeo.core.data.api

import com.romeo.core.data.api.dto.CharacterDTO
import com.romeo.core.data.api.dto.TokenDTO
import com.romeo.core.domain.entity.SignInEntity
import com.romeo.core.domain.entity.SignUpEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("$API_VERSION/singup/")
    fun signUp(@Body signUpEntity: SignUpEntity): Flow<TokenDTO>

    @POST("$API_VERSION/singin/")
    fun signIn(@Body signInEntity: SignInEntity): Flow<TokenDTO>

    @GET("$API_VERSION/post/list")
    fun getAllCharacters(): Flow<List<CharacterDTO>>

    @GET("$API_VERSION/post/{id}")
    fun getCharacter(@Path("id") id: Int): Flow<CharacterDTO>

    companion object {
        private const val API_VERSION = "v0"
        const val BASE_API_URL = "http://api.android-test-app.4-com.pro/"
    }
}