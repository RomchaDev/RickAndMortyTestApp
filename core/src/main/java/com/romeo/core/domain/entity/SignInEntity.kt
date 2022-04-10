package com.romeo.core.domain.entity

// It is possible to use SignUpEntity instead but it is bad practise since it can be changed
data class SignInEntity(
    val email: String,
    val password: String
)