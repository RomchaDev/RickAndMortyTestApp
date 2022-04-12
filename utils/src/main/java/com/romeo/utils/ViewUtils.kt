package com.romeo.utils

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope, block: (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        collect { t ->
            block(t)
        }
    }
}

fun <T> Flow<T>.launchWhenCreated(lifecycleScope: LifecycleCoroutineScope, block: (T) -> Unit) {

    lifecycleScope.launch {
        collect { t ->
            block(t)
        }
    }

}