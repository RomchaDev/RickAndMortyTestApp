package com.romeo.main.presentation.main

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.romeo.core.databinding.ItemCharacterBinding
import com.romeo.core.domain.entity.Character
import com.romeo.core.presentation.BaseFragment
import com.romeo.core.presentation.list.MainListAdapter
import com.romeo.core.presentation.navigation.global_actions.GlobalToCharDirections
import com.romeo.main.R
import com.romeo.main.databinding.FragmentCharactersBinding

abstract class AbstractCharactersFragment :
    BaseFragment<FragmentCharactersBinding, CharactersViewState, AbstractCharactersViewModel>(
        R.layout.fragment_characters
    ) {

    private var charactersAdapter: MainListAdapter<Character>? = null
    private var selectedItemBinding: ItemCharacterBinding? = null
    private var imageTransitionName = ""

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

    override fun navigate(navController: NavController, direction: NavDirections) {
        if (direction !is GlobalToCharDirections) {
            super.navigate(navController, direction)
            return
        }

        selectedItemBinding?.let { itemBinding ->
            val extras = FragmentNavigatorExtras(
                itemBinding.ivMain to imageTransitionName
            )

            navController.navigate(direction, extras)
        }
    }

    @CallSuper
    protected open fun bindListItem(pos: Int, binding: ItemCharacterBinding, data: Character) {
        binding.root.setOnClickListener {
            viewModel.onItemPressed(data.id)
        }

        selectedItemBinding = binding
        imageTransitionName = "trans_image_$pos"
        viewModel.onBindListItem(pos)
    }
}