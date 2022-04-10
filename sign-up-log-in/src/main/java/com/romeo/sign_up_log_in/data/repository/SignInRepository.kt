package com.romeo.sign_up_log_in.data.repository

import kotlinx.coroutines.flow.Flow

interface SignInRepository {
    fun signIn(email: String, password: String): Flow<String>
}