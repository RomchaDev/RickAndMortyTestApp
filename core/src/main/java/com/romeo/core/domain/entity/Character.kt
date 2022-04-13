package com.romeo.core.domain.entity

import android.os.Parcelable
import com.romeo.core.data.api.dto.CharacterDTO
import com.romeo.core.data.local.entity.CharacterLocal
import com.romeo.core.presentation.list.Content
import com.romeo.core.presentation.list.ListItem
import com.romeo.core.presentation.list.ListItemId
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    @ListItemId val id: Int,
    @Content val title: String,
    @Content val subtitle: String,
    @Content val imageUrl: String,
    @Content var isFavorite: Boolean,
    @Content val description: String?
) : ListItem<Character>, Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Character

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

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