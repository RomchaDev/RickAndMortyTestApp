package com.romeo.sign_up_log_in.presentation

import com.romeo.core.presentation.BaseViewModel

class SignUpLogInViewModel : BaseViewModel<SignUpLogInViewState>() {

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
}