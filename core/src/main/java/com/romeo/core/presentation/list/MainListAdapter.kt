package com.romeo.core.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.romeo.core.BR

/**
 * The adapter that has to be used for the each recycler view in app
 *
 * @param itemLayoutId - the map in which should be presented item layouts and
 * their view types. (view types are keys, layouts are values)
 *
 * @param bind - the code, that should be executed when the view is bound to
 * the viewHolder
 * */
open class MainListAdapter<I : ListItem<I>>(
    private val itemLayoutId: Map<Int, Int>,
    private val bind: ((ViewDataBinding, data: I) -> Unit)? = null
) : ListAdapter<I, MainListAdapter<I>.BaseViewHolder>(BaseDiffUtilCallback<I>()) {

    inner class BaseViewHolder(
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        /**
         * The method that injects data into xml and calls
         * bind method from adapter.
         *
         * @param data - data that will be injected into xml
         * */
        fun bind(data: I) {
            binding.setVariable(BR.data, data)
            bind?.invoke(binding, data)
        }
    }

    override fun getItemViewType(position: Int) =
        currentList[position].getViewType()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutId = itemLayoutId[viewType]!!

        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )

        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        const val TAG = "BASE_ADAPTER"

        fun oneItemMap(layoutId: Int) = mapOf(ListItem.DEFAULT_ITEM_VIEW_TYPE to layoutId)
    }
}