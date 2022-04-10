package com.romeo.core.domain.use_case

interface BaseUseCase<I : UseCaseArgument, O> {
    suspend fun execute(input: I) : O
}