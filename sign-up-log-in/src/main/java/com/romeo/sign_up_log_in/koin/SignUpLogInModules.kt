package com.romeo.sign_up_log_in.koin

import com.romeo.sign_up_log_in.data.repository.SignInRepository
import com.romeo.sign_up_log_in.data.repository.SignInRepositoryImpl
import com.romeo.sign_up_log_in.data.repository.SignUpRepository
import com.romeo.sign_up_log_in.data.repository.SignUpRepositoryImpl
import com.romeo.sign_up_log_in.domain.usecase.SignInUseCase
import com.romeo.sign_up_log_in.domain.usecase.SignUpUseCase
import com.romeo.sign_up_log_in.presentation.SignUpLogInViewModel
import com.romeo.sign_up_log_in.presentation.navigation.LogInToMainDirections
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signUpLogInModule = module {
    viewModel { SignUpLogInViewModel(get(), get(), get(), get()) }

    factory<SignUpRepository> { SignUpRepositoryImpl(get()) }
    factory<SignInRepository> { SignInRepositoryImpl(get()) }

    factory { SignUpUseCase(get(), get()) }
    factory { SignInUseCase(get(), get()) }

    factory { LogInToMainDirections() }
}