package com.romeo.main.presentation.main

import android.os.Bundle
import android.view.View
import com.romeo.core.databinding.ItemCharacterBinding
import com.romeo.core.domain.entity.Character
import com.romeo.core.presentation.BaseFragment
import com.romeo.core.presentation.list.MainListAdapter
import com.romeo.main.R
import com.romeo.main.databinding.FragmentCharactersBinding
import org.koin.android.viewmodel.ext.android.viewModel

class CharactersFragment :
    BaseFragment<FragmentCharactersBinding, CharactersViewState, CharactersViewModel>(
        R.layout.fragment_characters
    ) {

    override val viewModel: CharactersViewModel by viewModel()
    private var adapter: MainListAdapter<Character>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MainListAdapter(
            MainListAdapter.oneItemMap(R.layout.item_character)
        ) { binding, data ->

            binding as ItemCharacterBinding

            binding.root.setOnClickListener {
                viewModel.onItemPressed(data.id)
            }

            binding.ivHeart.setOnClickListener {
                viewModel.onLikePressed(data.id)
            }
        }

        binding.rvCharacters.adapter = adapter
    }

    override fun renderSuccess(data: CharactersViewState) {
        super.renderSuccess(data)
        adapter?.submitList(data.characters)
    }
}