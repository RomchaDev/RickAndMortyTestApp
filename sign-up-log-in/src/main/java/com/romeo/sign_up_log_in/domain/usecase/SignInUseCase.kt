package com.romeo.sign_up_log_in.domain.usecase

import com.romeo.core.data.repository.TokenRepository
import com.romeo.core.domain.use_case.BaseUseCase
import com.romeo.sign_up_log_in.data.repository.SignInRepository
import kotlinx.coroutines.flow.collect

class SignInUseCase(
    private val tokenRepository: TokenRepository,
    private val signInRepository: SignInRepository
) : BaseUseCase<SignInArgument, Unit> {

    override suspend fun execute(input: SignInArgument) {
        signInRepository.signIn(input.email, input.password).collect {
            tokenRepository.put(it)
        }
    }
}