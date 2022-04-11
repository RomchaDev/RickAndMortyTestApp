package com.romeo.main.presentation.main

import com.romeo.core.databinding.ItemCharacterBinding
import com.romeo.core.domain.entity.Character
import org.koin.android.viewmodel.ext.android.viewModel

class CharactersFragment : AbstractCharactersFragment() {

    override val viewModel: CharactersViewModel by viewModel()

    override fun bindListItem(binding: ItemCharacterBinding, data: Character) {
        super.bindListItem(binding, data)
        binding.ivHeart.setOnClickListener { viewModel.onLikePressed(data.id) }
    }
}