package com.romeo.core.presentation.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.romeo.core.R
import com.romeo.core.data.image.GlideImageLoader
import com.romeo.core.data.image.ImageLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@BindingAdapter("favorite")
fun favorite(iv: ImageView, isFavorite: Boolean) {

    val imgId = if (isFavorite) R.drawable.ic_like_filled else R.drawable.ic_like_border

    iv.setImageResource(imgId)
}

@BindingAdapter("loadImage")
fun loadImage(iv: ImageView, url: String?) {
    url?.let {
        imageLoadingCoroutineScope.launch {
            imageLoader.loadImage(
                target = iv,
                url = url,
                imageInsertCoroutineScope
            )
        }
    }
}

private val imageLoader: ImageLoader<ImageView> = GlideImageLoader()

private val imageLoadingCoroutineScope = CoroutineScope(
    Dispatchers.IO
            + SupervisorJob()
)

private val imageInsertCoroutineScope = CoroutineScope(
    Dispatchers.Main
            + SupervisorJob()
)