package com.romeo.sign_up_log_in.koin

import com.romeo.sign_up_log_in.presentation.SignUpLogInViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signUpLogInModule = module {
    viewModel { SignUpLogInViewModel() }
}