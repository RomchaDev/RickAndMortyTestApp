package com.romeo.core.presentation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.google.gson.Gson
import com.romeo.core.domain.entity.AppStateEntity
import com.romeo.core.presentation.navigation.NavigationCommand
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel<D : AppStateEntity> : ViewModel() {

    // Used SharedFlow because StateFlow has replays only one action which can cause some problems.
    protected val mDataFlow = MutableSharedFlow<AppState<D>>(
        replay = 2,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    val dataFlow get() = mDataFlow.asSharedFlow()

    protected val mNavigationFlow = MutableSharedFlow<NavigationCommand>(
        replay = 2,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    val navigationFlow get() = mNavigationFlow.asSharedFlow()

    protected val ioCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private val mainCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected open fun cancelJob() {
        ioCoroutineScope.coroutineContext.cancelChildren()
    }

    open fun handleError(error: Throwable) {
        error.printStackTrace()
        runAsync {
            mDataFlow.emit(AppState.Error(error))
        }
    }

    open fun onViewInit() {
    }

    protected fun runAsync(block: suspend () -> Unit) =
        ioCoroutineScope.launch {
            block()
        }


    protected fun <T> runAsyncWithResult(block: suspend () -> T) =
        ioCoroutineScope.async {
            block()
        }

    protected fun runOnMainThread(block: suspend () -> Unit) =
        mainCoroutineScope.launch {
            block()
        }

    protected fun emitSuccess(entity: D) = runAsync {
        mDataFlow.emit(AppState.Success(entity))
    }

    protected fun emitError(throwable: Throwable) = runAsync {
        mDataFlow.emit(AppState.Error(throwable))
    }

    protected fun emitMessage(message: String) = runAsync {
        mDataFlow.emit(AppState.Message(message))
    }

    protected fun navigateTo(
        directions: NavDirections,
    ) = runAsync {
        mNavigationFlow.emit(NavigationCommand.To(directions))
    }

    protected fun navigateBack() = runAsync {
        mNavigationFlow.emit(NavigationCommand.Back)
    }

    protected fun subscribeToResult(
        key: String = NavigationCommand.DEFAULT_RESULT_KEY
    ) = runAsync {
        mNavigationFlow.emit(NavigationCommand.SubscribeResult(key))
    }

    protected fun <T> setResult(
        result: T,
        key: String = NavigationCommand.DEFAULT_RESULT_KEY
    ) = runAsync {
        val resultStr = Gson().toJson(result)
        mNavigationFlow.emit(NavigationCommand.SetResult(resultStr, key))
    }

    open fun onNavigationResult(key: String, resultJson: String) {
    }
}