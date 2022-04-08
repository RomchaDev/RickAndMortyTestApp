package com.romeo.core.presentation.navigation

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class To(val direction: NavDirections) : NavigationCommand()
    data class SetResult(
        val result: String,
        val key: String = DEFAULT_RESULT_KEY
    ) : NavigationCommand()

    data class SubscribeResult(
        val key: String = DEFAULT_RESULT_KEY
    ) : NavigationCommand()

    object Back : NavigationCommand()

    companion object {
        const val DEFAULT_RESULT_KEY = "DEFAULT_RESULT_KEY"
    }
}