package com.romeo.core.data.api

import com.google.gson.Gson
import com.romeo.core.data.api.api_exceptions.*
import com.romeo.core.data.api.dto.ErrorResponseDTO
import com.romeo.core.data.repository.TokenRepository
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

class MainInterceptor(
    private val tokenRepository: TokenRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {

        val token = tokenRepository.get().take(1).single()

        val newRequest = token?.let {
            chain.request().newBuilder()
                .addHeader("Authorization", "Token $token")
                .build()
        } ?: chain.request()

        val response = chain.proceed(newRequest)
        val responseCode = response.code()
        val responseBody = response.body()
        processResponseCode(responseCode, responseBody)

        return@runBlocking response
    }

    private fun processResponseCode(responseCode: Int, responseBody: ResponseBody?) {

        if (responseCode == RESPONSE_CODE) {
            responseBody?.charStream()?.readText()?.let { body ->

                val response = Gson().fromJson(body, ErrorResponseDTO::class.java)

                val responseMessage =
                    response.email?.get(0) ?: response.password?.get(0) ?: "Unknown error"

                throw when (responseMessage) {
                    INVALID_PASSWORD_MESSAGE -> InvalidPasswordException(responseMessage)
                    USER_NOT_EXIST_MESSAGE -> UserNotExistsException(responseMessage)
                    USER_ALREADY_EXISTS_MESSAGE -> UserAlreadyExistsException(responseMessage)
                    PASSWORD_TOO_SHORT_MESSAGE -> PasswordTooShortException(responseMessage)
                    PASSWORD_NUMERIC_ONLY_MESSAGE -> PasswordNumericOnlyException(responseMessage)
                    PASSWORD_TOO_COMMON_MESSAGE -> PasswordTooCommonException(responseMessage)
                    else -> IOException(responseMessage)
                }
            }
        }
    }

    companion object {
        // It would be great to use response codes, but api responses with 400 code for all errors

        private const val RESPONSE_CODE = 400

        private const val INVALID_PASSWORD_MESSAGE =
            "Invalid password"

        private const val USER_NOT_EXIST_MESSAGE =
            "User with this email does not exist"

        private const val USER_ALREADY_EXISTS_MESSAGE =
            "User with this email already exists."

        private const val PASSWORD_TOO_SHORT_MESSAGE =
            "This password is too short. It must contain at least 8 characters."

        private const val PASSWORD_NUMERIC_ONLY_MESSAGE =
            "This password is entirely numeric."

        private const val PASSWORD_TOO_COMMON_MESSAGE =
            "This password is too common."
    }
}