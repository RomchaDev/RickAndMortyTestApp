package com.romeo.sign_up_log_in.domain.usecase

import com.romeo.core.domain.use_case.UseCaseArgument

data class SignUpArgument(
    val email: String,
    val password: String
) : UseCaseArgument

data class SignInArgument(
    val email: String,
    val password: String
) : UseCaseArgument