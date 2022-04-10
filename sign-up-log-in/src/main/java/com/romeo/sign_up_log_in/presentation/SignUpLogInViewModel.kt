package com.romeo.sign_up_log_in.presentation

import com.romeo.core.presentation.BaseViewModel
import kotlinx.coroutines.delay

class SignUpLogInViewModel : BaseViewModel<SignUpLogInViewState>() {

    override fun onViewInit() {
        runAsync {
            delay(LAUNCH_TIME)
            emitSuccess(SignUpLogInViewState.SIGN_UP)
        }
    }

    fun onSignUpToLogInPressed() {
        emitSuccess(SignUpLogInViewState.LOG_IN)
    }

    fun onLogInToSignUpPressed() {
        emitSuccess(SignUpLogInViewState.SIGN_UP)
    }

    fun onSignUpPressed() {
        emitMessage("Sign Up")
    }

    fun onLogInPressed() {
        emitMessage("Log In")
    }

    companion object {
        private const val LAUNCH_TIME = 1000L
    }
}