package com.romeo.core.presentation.list

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtilCallback<T : ListItem<T>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        newItem.areItemsTheSame(oldItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        newItem.areContentsTheSame(oldItem)

    override fun getChangePayload(oldItem: T, newItem: T): Any {
        return Any()
    }
}