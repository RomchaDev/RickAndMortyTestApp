package com.romeo.core.presentation.list

fun <T> List<T>.replace(pos: Int, newItem: T): List<T> {
    if (pos !in 0 until size - 1) return this

    val newList = mutableListOf<T>()
    newList.addAll(this)
    newList[pos] = newItem
    return newList
}

fun <T> List<T>.replace(newItem: T, predicate: (T) -> Boolean): List<T> {
    var pos = -1

    forEachIndexed { i, el ->
        if (predicate(el)) {
            pos = i
            return@forEachIndexed
        }
    }

    if (pos == -1) return this
    else return replace(pos, newItem)
}