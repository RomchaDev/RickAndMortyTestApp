package com.romeo.sign_up_log_in.presentation

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import com.romeo.core.presentation.BaseFragment
import com.romeo.sign_up_log_in.R
import com.romeo.sign_up_log_in.databinding.FragmentSignUpLogInBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SignUpLogInFragment :
    BaseFragment<FragmentSignUpLogInBinding, SignUpLogInViewState, SignUpLogInViewModel>(
        R.layout.fragment_sign_up_log_in
    ) {

    override val viewModel: SignUpLogInViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener { viewModel.onSignUpPressed() }
        binding.btnSignIn.setOnClickListener { viewModel.onLogInPressed() }

        binding.layoutActionSignUp.setOnClickListener { viewModel.onLogInToSignUpPressed() }
        binding.layoutActionSignIn.setOnClickListener { viewModel.onSignUpToLogInPressed() }

        binding.btnSignIn.isClickable = false
    }

    override fun renderSuccess(data: SignUpLogInViewState) {
        with (binding.root as MotionLayout) {
            when (data) {
                SignUpLogInViewState.SIGN_UP ->  {
                    transitionToState(R.id.state_sign_up)
                    binding.btnSignUp.isClickable = true
                    binding.btnSignIn.isClickable = false
                }

                SignUpLogInViewState.LOG_IN -> {
                    transitionToState(R.id.state_sign_in)
                    binding.btnSignUp.isClickable = false
                    binding.btnSignIn.isClickable = true
                }
            }
        }
    }
}