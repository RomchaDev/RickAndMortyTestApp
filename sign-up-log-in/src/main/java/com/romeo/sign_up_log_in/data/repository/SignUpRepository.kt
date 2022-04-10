package com.romeo.sign_up_log_in.data.repository

import kotlinx.coroutines.flow.Flow

interface SignUpRepository {
    fun signUp(email: String, password: String): Flow<String>
}