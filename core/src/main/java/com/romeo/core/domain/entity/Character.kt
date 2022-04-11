package com.romeo.core.domain.entity

import com.google.gson.annotations.SerializedName
import com.romeo.core.presentation.list.Content
import com.romeo.core.presentation.list.ListItemId

data class Character(
    @ListItemId val id: Int,
    @Content val title: String,
    @Content val subtitle: String,

    @Content
    @SerializedName("image")
    val imageUrl: String,

    @Content
    @SerializedName("favorite")
    val isFavorite: Boolean,

    val description: String?
)