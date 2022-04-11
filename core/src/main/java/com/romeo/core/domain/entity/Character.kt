package com.romeo.core.domain.entity

import com.romeo.core.data.api.dto.CharacterDTO
import com.romeo.core.data.local.entity.CharacterLocal

data class Character(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val isFavorite: Boolean,
    val description: String?
) {
    companion object {
        fun fromDTO(characterDTO: CharacterDTO) =
            with(characterDTO) {
                Character(
                    id = id,
                    title = title,
                    subtitle = subtitle,
                    imageUrl = imageUrl,
                    isFavorite = isFavorite,
                    description = description
                )
            }

        fun fromLocal(characterLocal: CharacterLocal) =
            with(characterLocal) {
                Character(
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