package com.romeo.core.data.api.dto

import com.google.gson.annotations.SerializedName
import com.romeo.core.domain.entity.Character
import com.romeo.core.presentation.list.Content
import com.romeo.core.presentation.list.ListItemId

data class CharacterDTO(
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
) {
    companion object {
        fun fromDomain(character: Character) =
            with(character) {
                CharacterDTO(
                    id = id,
                    title = title,
                    subtitle = subtitle,
                    imageUrl = imageUrl,
                    isFavorite = isFavorite,
                    description = description
                )
            }
    }
}