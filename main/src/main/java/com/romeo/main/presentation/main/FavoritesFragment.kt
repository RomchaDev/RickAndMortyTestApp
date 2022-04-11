package com.romeo.main.presentation.main

import android.os.Bundle
import android.view.View
import com.romeo.core.databinding.ItemCharacterBinding
import com.romeo.core.domain.entity.Character
import com.romeo.main.R
import org.koin.android.viewmodel.ext.android.viewModel

class FavoritesFragment : AbstractCharactersFragment() {
    override val viewModel: FavoritesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvPageHeader.setText(R.string.favorites)
    }

    override fun bindListItem(binding: ItemCharacterBinding, data: Character) {
        super.bindListItem(binding, data)

        binding.ivHeart.visibility = View.GONE
    }
}