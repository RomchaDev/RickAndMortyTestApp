package com.romeo.core.data

import com.romeo.core.BuildConfig

class PrivateConstantsHolder {
    val tokenKey = BuildConfig.TOKEN_KEY

    companion object {
        const val TOKEN_KEY_QUALIFIER = "TOKEN_KEY_QUALIFIER"
    }
}