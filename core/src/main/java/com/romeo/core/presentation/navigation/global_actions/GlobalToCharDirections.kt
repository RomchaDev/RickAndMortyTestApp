package com.romeo.core.presentation.navigation.global_actions

import android.os.Bundle
import androidx.navigation.NavDirections
import com.romeo.core.R

data class GlobalToCharDirections(
    override val arguments: Bundle = Bundle()
) : NavDirections {
    override val actionId: Int = R.id.action_global_to_char
}