package com.romeo.core.data.api.dto

import com.google.gson.annotations.SerializedName
import com.romeo.core.domain.entity.Character

data class CharacterDTO(
    val id: Int,
    val title: String,
    val subtitle: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("favorite") val isFavorite: Boolean,
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