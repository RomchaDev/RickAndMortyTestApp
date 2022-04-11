package com.romeo.main.presentation.view

import android.widget.ImageView

import androidx.databinding.BindingAdapter
import com.romeo.main.R

@BindingAdapter("favorite")
fun favorite(iv: ImageView, isFavorite: Boolean) {

    val imgId = if (isFavorite) R.drawable.ic_like_filled else R.drawable.ic_like_border

    iv.setImageResource(imgId)
}