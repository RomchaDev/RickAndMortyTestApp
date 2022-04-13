package com.romeo.main.presentation.main.favorites

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.ItemTouchHelper
import com.romeo.core.databinding.ItemCharacterBinding
import com.romeo.core.domain.entity.Character
import com.romeo.main.R
import com.romeo.main.presentation.main.AbstractCharactersFragment
import org.koin.android.viewmodel.ext.android.viewModel

class FavoritesFragment : AbstractCharactersFragment() {
    override val viewModel: FavoritesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvPageHeader.setText(R.string.favorites)

        val icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_like_border)
        val background =
            Color.valueOf(
                requireContext().getColor(R.color.grey_dark)
            ).toDrawable()

        icon?.let {
            val callback = SwipeCallback(icon, background) { pos ->
                viewModel.deleteFromFavorites(pos)
            }

            ItemTouchHelper(callback).attachToRecyclerView(binding.rvCharacters)
        }

    }

    override fun bindListItem(pos: Int, binding: ItemCharacterBinding, data: Character) {
        super.bindListItem(pos, binding, data)

        binding.ivHeart.visibility = View.GONE
    }
}