package com.romeo.core.data.datasource.remote

import com.romeo.core.data.api.dto.TokenDTO
import com.romeo.core.domain.entity.SignInEntity
import com.romeo.core.domain.entity.SignUpEntity
import kotlinx.coroutines.flow.Flow

interface SingUpSingInDatasource {
    fun signUp(signUpEntity: SignUpEntity): Flow<TokenDTO>

    fun signIn(signInEntity: SignInEntity): Flow<TokenDTO>
}