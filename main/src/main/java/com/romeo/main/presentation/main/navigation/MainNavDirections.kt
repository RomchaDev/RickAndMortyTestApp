package com.romeo.main.presentation.main.navigation

import android.os.Bundle
import androidx.navigation.NavDirections
import com.romeo.main.R

interface CharacterDirections : NavDirections

data class CharactersToCharDirections(
    override val arguments: Bundle = Bundle()
) : CharacterDirections {
    override val actionId: Int = R.id.action_characters_to_char
}

data class FavoritesToCharDirections(
    override val arguments: Bundle = Bundle()
) : CharacterDirections {
    override val actionId: Int = R.id.action_favorites_to_char
}

data class GlobalToCharDirections(
    override val arguments: Bundle = Bundle()
) : CharacterDirections {
    override val actionId: Int = R.id.action_global_to_char
}