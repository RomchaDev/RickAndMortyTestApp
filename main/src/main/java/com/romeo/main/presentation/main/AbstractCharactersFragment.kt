package com.romeo.main.presentation.main

import android.os.Bundle
import android.view.View
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

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        charactersAdapter = MainListAdapter(
            MainListAdapter.oneItemMap(R.layout.item_character)
        ) { binding, data ->

            bindListItem(binding as ItemCharacterBinding, data)
        }

        binding.ivLogOut.setOnClickListener {
            viewModel.onLogOutPressed()
        }

        binding.rvCharacters.adapter = charactersAdapter
    }

    override fun renderSuccess(data: CharactersViewState) {
        super.renderSuccess(data)
        charactersAdapter?.submitList(data.characters)
    }

    @CallSuper
    protected open fun bindListItem(binding: ItemCharacterBinding, data: Character) {
        binding.root.setOnClickListener {
            viewModel.onItemPressed(data.id)
        }
    }
}