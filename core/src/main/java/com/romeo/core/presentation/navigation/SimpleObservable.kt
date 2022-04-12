package com.romeo.core.presentation.navigation

open class SimpleObservable<T> {
    protected var onEmit: ((T) -> Unit)? = null

    fun subscribe(block: (T) -> Unit) {
        onEmit = block
    }
}

class MutableSimpleObservable<T> : SimpleObservable<T>() {

    fun emit(value: T) {
        onEmit?.invoke(value)
    }

    fun toSimpleObservable() = this as SimpleObservable<T>
}