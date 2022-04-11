package com.romeo.core.data.local.entity

import com.romeo.core.domain.entity.Character
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class CharacterLocal() : RealmObject() {

    @PrimaryKey
    var id: Int = 0
    @Required
    var title: String = ""
    @Required
    var subtitle: String = ""
    @Required
    var imageUrl: String = ""
    var isFavorite: Boolean = false
    var description: String? = null

    constructor(
        id: Int,
        title: String,
        subtitle: String,
        imageUrl: String,
        isFavorite: Boolean,
        description: String?
    ) : this() {
        this.id = id
        this.title = title
        this.subtitle = subtitle
        this.imageUrl = imageUrl
        this.isFavorite = isFavorite
        this.description = description
    }

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