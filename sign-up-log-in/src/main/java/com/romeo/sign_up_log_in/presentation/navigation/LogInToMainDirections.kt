package com.romeo.sign_up_log_in.presentation.navigation

import android.os.Bundle
import androidx.navigation.NavDirections
import com.romeo.sign_up_log_in.R

class LogInToMainDirections(
    override val arguments: Bundle = Bundle()
) : NavDirections {
    override val actionId: Int = R.id.action_login_to_main
}