package com.romeo.main.presentation.main.characters

import com.romeo.core.databinding.ItemCharacterBinding
import com.romeo.core.domain.entity.Character
import com.romeo.main.presentation.main.AbstractCharactersFragment
import org.koin.android.viewmodel.ext.android.viewModel

class CharactersFragment : AbstractCharactersFragment() {

    override val viewModel: CharactersViewModel by viewModel()

    override fun bindListItem(pos: Int, binding: ItemCharacterBinding, data: Character) {
        super.bindListItem(pos, binding, data)
        binding.ivHeart.setOnClickListener { viewModel.onLikePressed(data.id) }
    }
}