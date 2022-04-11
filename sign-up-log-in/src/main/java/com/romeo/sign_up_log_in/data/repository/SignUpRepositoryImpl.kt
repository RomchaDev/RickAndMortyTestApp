package com.romeo.sign_up_log_in.data.repository

import com.romeo.core.data.datasource.remote.SingUpSingInDatasource
import com.romeo.core.domain.entity.SignUpEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SignUpRepositoryImpl(
    private val remoteDatasource: SingUpSingInDatasource
) : SignUpRepository {
    override fun signUp(email: String, password: String): Flow<String> {
        return remoteDatasource.signUp(SignUpEntity(email, password)).map { it.token }
    }
}