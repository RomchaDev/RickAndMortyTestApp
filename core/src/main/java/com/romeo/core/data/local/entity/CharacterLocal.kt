package com.romeo.core.data.local.entity

import com.romeo.core.domain.entity.Character
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class CharacterLocal(
    @PrimaryKey val id: Int,
    @Required val title: String,
    @Required val subtitle: String,
    @Required val imageUrl: String,
    @Required val isFavorite: Boolean,
    @Required val description: String?
) : RealmObject() {
    companion object {
        fun fromDomain(character: Character) =
            with(character) {
                CharacterLocal(
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