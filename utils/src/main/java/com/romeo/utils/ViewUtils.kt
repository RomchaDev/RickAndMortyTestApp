package com.romeo.utils

import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> Flow<T>.launchWhenStarted(viewLifecycleOwner: LifecycleOwner, block: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {

        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            collect {
                it?.let {
                    block(it)
                }
            }
        }
    }
}