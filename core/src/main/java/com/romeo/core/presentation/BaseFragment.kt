package com.romeo.core.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.romeo.core.domain.entity.AppStateEntity
import com.romeo.core.presentation.navigation.NavigationCommand
import com.romeo.utils.launchWhenStarted

abstract class BaseFragment<VB : ViewDataBinding, D : AppStateEntity, VM : BaseViewModel<D>>(
    private val layoutID: Int
) : Fragment() {

    private var bindingNullable: VB? = null
    protected val binding get() = bindingNullable!!
    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<VB>(
            inflater,
            layoutID,
            container,
            false
        ).apply { bindingNullable = this }
            .root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dataFlow.launchWhenStarted(lifecycleScope) { state ->
            renderData(state)
        }

        viewModel.navigationFlow.launchWhenStarted(lifecycleScope) { command ->
            renderNavigation(command)
        }

        viewModel.onViewInit()
    }

    protected fun setNavigationPaddingFor(view: View) {
        val resourceId: Int = resources
            .getIdentifier("navigation_bar_height", "dimen", "android")

        val navHeight = resources.getDimensionPixelSize(resourceId)

        with(view) {
            setPadding(paddingLeft, paddingTop, paddingRight, navHeight)
        }
    }

    protected open fun renderData(state: AppState<D>) {
        when (state) {
            is AppState.Success -> renderSuccess(state.data)
            is AppState.Error -> renderError(state.error)
            is AppState.Message -> renderMessage(state.message)
            is AppState.Loading -> setLoading(true)
        }
    }

    private fun renderNavigation(command: NavigationCommand) {
        val navController = findNavController()

        when (command) {
            is NavigationCommand.To -> navController.navigate(command.direction)
            is NavigationCommand.Back -> back(navController)
            is NavigationCommand.SubscribeResult -> subscribeToNavigationResult(command.key)
            is NavigationCommand.SetResult -> setNavigationResult(
                command.key,
                command.result
            )
        }
    }

    private fun back(navController: NavController) {
        val stack = navController.backQueue
        print(stack.size)
        navController.previousBackStackEntry?.let { entery ->
            navController.popBackStack(entery.destination.id, true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingNullable = null
    }

    protected open fun renderSuccess(data: D) {
        setLoading(false)
    }

    protected open fun renderError(error: Throwable) {
        setLoading(false)
        error.message?.let { showMessage(it) }
    }

    protected open fun renderMessage(message: String) {
        setLoading(false)
        showMessage(message)
    }

    //Can be empty
    protected open fun setLoading(isLoading: Boolean) {
    }

    open fun showMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    protected fun subscribeToNavigationResult(key: String = NavigationCommand.DEFAULT_RESULT_KEY) {
        setFragmentResultListener(key) { _, bundle ->
            bundle.getString(key)?.let {
                viewModel.onNavigationResult(key, it)
            }
        }
    }

    private fun setNavigationResult(key: String, result: String) {
        setFragmentResult(key, bundleOf(key to result))
    }
}