package com.romeo.core.data.image

import kotlinx.coroutines.CoroutineScope

interface ImageLoader<T> {
    fun loadImage(
        target: T,
        url: String,
        listener: ImageLoadingListener? = null,
        loadingCode: Int? = null
    )

    fun loadImage(
        target: T,
        url: String,
        imageInsertScope: CoroutineScope

    )
}