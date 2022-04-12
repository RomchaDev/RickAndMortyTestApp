package com.romeo.character.presentation

import android.os.Bundle
import android.view.View
import com.romeo.character.R
import com.romeo.character.databinding.FragmentCharacterBinding
import com.romeo.core.domain.entity.Character
import com.romeo.core.presentation.BaseFragment
import com.romeo.core.presentation.navigation.CHAR_KEY
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CharacterFragment :
    BaseFragment<FragmentCharacterBinding, CharacterViewState, CharacterViewModel>(
        R.layout.fragment_character
    ) {

    private var isFav = false
    private var char: Character? = null

    init {
        char = arguments?.getParcelable(CHAR_KEY)
        char?.let {
            isFav = it.isFavorite
        }
    }

    override val viewModel: CharacterViewModel by viewModel {
        val char = arguments?.getParcelable<Character>(CHAR_KEY)
        parametersOf(char)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        char?.let { binding.character = char }

        binding.bgAddToFav.setOnClickListener {
            viewModel.onChangeFavoriteStatusPressed()
        }

        binding.bgRemoveFromFav.setOnClickListener {
            viewModel.onChangeFavoriteStatusPressed()
        }

        binding.ivBack.setOnClickListener {
            viewModel.onBackPressed()
        }
    }

    override fun renderSuccess(data: CharacterViewState) {
        when (data) {
            is CharacterViewState.CharacterState -> {
                parseFavJumping(data.character.isFavorite)
                binding.character = data.character
            }

            is CharacterViewState.ToAddToFav ->
                binding.coordinatorButtons.transitionToState(R.id.state_add_to_fav)

            is CharacterViewState.ToRemoveFromFav ->
                binding.coordinatorButtons.transitionToState(R.id.state_remove_from_fav)
        }
    }

    private fun parseFavJumping(isFav: Boolean) {
        if (isFav) binding.coordinatorButtons.jumpToState(R.id.state_remove_from_fav)
        else binding.coordinatorButtons.jumpToState(R.id.state_add_to_fav)
    }
}