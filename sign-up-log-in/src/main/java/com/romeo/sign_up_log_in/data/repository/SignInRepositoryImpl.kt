package com.romeo.sign_up_log_in.data.repository

import com.romeo.core.data.datasource.remote.RemoteDatasource
import com.romeo.core.domain.entity.SignInEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SignInRepositoryImpl(
    private val remoteDatasource: RemoteDatasource
) : SignInRepository {
    override fun signIn(email: String, password: String): Flow<String> {
        return remoteDatasource.signIn(SignInEntity(email, password)).map {
            it.token
        }
    }
}