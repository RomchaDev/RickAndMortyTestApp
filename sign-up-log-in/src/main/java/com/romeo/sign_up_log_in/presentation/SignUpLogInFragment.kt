package com.romeo.sign_up_log_in.presentation

import addTransitionCompletedListener
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import com.romeo.core.presentation.BaseFragment
import com.romeo.sign_up_log_in.R
import com.romeo.sign_up_log_in.databinding.FragmentSignUpLogInBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SignUpLogInFragment :
    BaseFragment<FragmentSignUpLogInBinding, SignUpLogInViewState, SignUpLogInViewModel>(
        R.layout.fragment_sign_up_log_in
    ) {

    override val viewModel: SignUpLogInViewModel by viewModel()
    private var currentSceneId = R.id.state_sign_up

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener { viewModel.onSignUpPressed() }
        binding.btnSignIn.setOnClickListener { viewModel.onLogInPressed() }

        binding.layoutActionSignUp.setOnClickListener { viewModel.onLogInToSignUpPressed() }
        binding.layoutActionSignIn.setOnClickListener { viewModel.onSignUpToLogInPressed() }

        (binding.root as MotionLayout).addTransitionCompletedListener {
            val curButton =
                if (currentSceneId == R.id.state_sign_up) binding.btnSignUp
                else binding.btnSignIn

            curButton.isClickable = areButtonsActive()
        }

        initTextInputs()
        binding.btnSignUp.setActive(false)
        binding.btnSignIn.setActive(false)

        binding.viewModel = viewModel
    }

    override fun renderSuccess(data: SignUpLogInViewState) {
        with(binding.root as MotionLayout) {
            when (data) {
                SignUpLogInViewState.SIGN_UP -> {
                    transitionToState(R.id.state_sign_up)
                }

                SignUpLogInViewState.LOG_IN -> {
                    transitionToState(R.id.state_sign_in)
                }
            }
        }
    }

    private fun initTextInputs() {
        binding.tlEmail.setIconListener()
        binding.tlPassword.setIconListener()
        binding.tlEmail.setButtonActiveListeners()
        binding.tlPassword.setButtonActiveListeners()
    }

    private fun TextInputLayout.setIconListener() {
        editText?.setOnFocusChangeListener { _, hasFocus ->
            val color = requireContext().getColor(
                if (hasFocus || this.editText?.text.toString().isNotEmpty())
                    R.color.purple_main
                else
                    R.color.hint
            )

            setStartIconTintList(ColorStateList.valueOf(color))
        }
    }

    private fun TextInputLayout.setButtonActiveListeners() {
        editText?.addTextChangedListener {
            setCorrectButtonStates()
        }
    }

    private fun setCorrectButtonStates() {
        val isActive = areButtonsActive()

        binding.btnSignIn.setActive(isActive)
        binding.btnSignUp.setActive(isActive)
    }

    private fun areButtonsActive() = (
            binding.tlEmail.editText?.text.toString().isNotEmpty() &&
                    binding.tlPassword.editText?.text.toString().isNotEmpty()
            )

    private fun Button.setActive(isActive: Boolean) {
        isClickable = isActive
        val color =
            if (isActive)
                null
            else
                requireContext().getColor(R.color.inactive_foreground)

        foreground = color?.toDrawable()
    }
}