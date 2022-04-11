package com.romeo.core.data.image

interface ImageLoader<T> {
    fun loadImage(
        target: T,
        url: String,
        listener: ImageLoadingListener? = null,
        loadingCode: Int? = null
    )
}