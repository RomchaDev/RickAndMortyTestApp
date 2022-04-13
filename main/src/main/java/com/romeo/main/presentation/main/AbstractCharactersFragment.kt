package com.romeo.main.presentation.main

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.CallSuper
import com.romeo.core.databinding.ItemCharacterBinding
import com.romeo.core.domain.entity.Character
import com.romeo.core.presentation.BaseFragment
import com.romeo.core.presentation.list.MainListAdapter
import com.romeo.main.R
import com.romeo.main.databinding.FragmentCharactersBinding

abstract class AbstractCharactersFragment :
    BaseFragment<FragmentCharactersBinding, CharactersViewState, AbstractCharactersViewModel>(
        R.layout.fragment_characters
    ) {

    private var charactersAdapter: MainListAdapter<Character>? = null

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return if (enter) {
            AnimationUtils.loadAnimation(context, R.anim.nav_default_enter_anim)
        } else {
            AnimationUtils.loadAnimation(context, R.anim.nav_default_exit_anim)
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        charactersAdapter = MainListAdapter(
            MainListAdapter.oneItemMap(R.layout.item_character)
        ) { pos, binding, data ->

            bindListItem(pos, binding as ItemCharacterBinding, data)
        }

        binding.ivLogOut.setOnClickListener {
            viewModel.onLogOutPressed()
        }

        binding.rvCharacters.adapter = charactersAdapter

        binding.swipeToRefresh.setOnRefreshListener {
            binding.swipeToRefresh.isRefreshing = true
            viewModel.update()
        }
    }

    override fun renderSuccess(data: CharactersViewState) {
        super.renderSuccess(data)
        charactersAdapter?.submitList(data.characters)
        binding.swipeToRefresh.isRefreshing = false
    }

    @CallSuper
    protected open fun bindListItem(pos: Int, binding: ItemCharacterBinding, data: Character) {
        binding.root.setOnClickListener {
            viewModel.onItemPressed(data.id)
        }

        viewModel.onBindListItem(pos)
    }
}