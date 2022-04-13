package com.romeo.sign_up_log_in.presentation

import androidx.databinding.ObservableField
import com.romeo.core.data.repository.TokenRepository
import com.romeo.core.presentation.BaseViewModel
import com.romeo.sign_up_log_in.domain.usecase.SignInArgument
import com.romeo.sign_up_log_in.domain.usecase.SignInUseCase
import com.romeo.sign_up_log_in.domain.usecase.SignUpArgument
import com.romeo.sign_up_log_in.domain.usecase.SignUpUseCase
import com.romeo.sign_up_log_in.presentation.navigation.LogInToMainDirections
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

class SignUpLogInViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val tokenRepository: TokenRepository,
    private val mainDirections: LogInToMainDirections
) : BaseViewModel<SignUpLogInViewState>() {

    val email = ObservableField("")
    val password = ObservableField("")

    override fun onViewInit() {
        runAsync {
            tokenRepository.get().collect { token ->
                token?.let {
                    navigateTo(mainDirections)
                } ?: run {
                    print(token)
                    delay(LAUNCH_TIME)
                    emitSuccess(SignUpLogInViewState.SIGN_UP)
                }
            }
        }
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        print(error.message)
    }

    fun onSignUpToLogInPressed() {
        emitSuccess(SignUpLogInViewState.LOG_IN)
    }

    fun onLogInToSignUpPressed() {
        emitSuccess(SignUpLogInViewState.SIGN_UP)
    }

    fun onSignUpPressed() {
        if (!email.get().isNullOrEmpty() && !password.get().isNullOrEmpty())
            runAsync {
                signUpUseCase.execute(SignUpArgument(email.get()!!, password.get()!!))
            }
    }

    fun onLogInPressed() {
        if (!email.get().isNullOrEmpty() && password.get() != null)
            runAsync {
                signInUseCase.execute(SignInArgument(email.get()!!, password.get()!!))
            }
    }

    companion object {
        private const val LAUNCH_TIME = 2000L
    }
}