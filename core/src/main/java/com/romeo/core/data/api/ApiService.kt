package com.romeo.core.data.api

import com.romeo.core.data.api.dto.CharacterDTO
import com.romeo.core.data.api.dto.CharactersResponse
import com.romeo.core.data.api.dto.TokenDTO
import com.romeo.core.domain.entity.Character
import com.romeo.core.domain.entity.SignInEntity
import com.romeo.core.domain.entity.SignUpEntity
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @POST("$API_VERSION/singup/")
    fun signUp(@Body signUpEntity: SignUpEntity): Flow<TokenDTO>

    @POST("$API_VERSION/singin/")
    fun signIn(@Body signInEntity: SignInEntity): Flow<TokenDTO>

    @GET("$API_VERSION/post/list")
    fun getAllCharacters(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Flow<CharactersResponse>

    @GET("$API_VERSION/post/{id}")
    fun getCharacter(
        @Path("id") id: Int
    ): Flow<CharacterDTO>

    @PUT("$API_VERSION/post/{id}")
    fun changeImage(
        @Path("id") id: Int,
        @Body image: RequestBody
    ): Flow<Character>

    companion object {
        private const val API_VERSION = "v0"
        const val BASE_API_URL = "http://api.android-test-app.4-com.pro/"
    }
}