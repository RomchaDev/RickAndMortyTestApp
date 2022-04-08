package com.romeo.utils

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun <T> Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope, block: (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        collect { t ->
            block(t)
        }
    }
}

fun Context.dip(value: Int) =
    (value * resources.displayMetrics.density).toInt()

fun View.animateViewHeight(start: Int, end: Int, duration: Long) {
    val animator = ValueAnimator.ofInt(start, end)
    animator.duration = duration
    animator.addUpdateListener { anim ->
        val h = anim.animatedValue as Int
        layoutParams.height = h
        println(h)
        requestLayout()
        if (h == 1) layoutParams.height = -3
    }

    animator.start()
}

val Number.toPx get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics)