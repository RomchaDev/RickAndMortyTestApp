package com.romeo.sign_up_log_in.domain.usecase

import com.romeo.core.data.repository.TokenRepository
import com.romeo.core.domain.use_case.BaseUseCase
import com.romeo.sign_up_log_in.data.repository.SignUpRepository
import kotlinx.coroutines.flow.collect

class SignUpUseCase(
    private val tokenRepository: TokenRepository,
    private val signUpRepository: SignUpRepository
) : BaseUseCase<SignUpArgument, Unit> {

    override suspend fun execute(input: SignUpArgument) {
        signUpRepository.signUp(input.email, input.password).collect {
            tokenRepository.put(it)
        }
    }
}