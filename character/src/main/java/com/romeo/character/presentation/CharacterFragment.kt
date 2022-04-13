package com.romeo.character.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.romeo.character.R
import com.romeo.character.databinding.FragmentCharacterBinding
import com.romeo.core.domain.entity.Character
import com.romeo.core.presentation.BaseFragment
import com.romeo.core.presentation.navigation.CHAR_KEY
import com.romeo.utils.getByteArray
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CharacterFragment :
    BaseFragment<FragmentCharacterBinding, CharacterViewState, CharacterViewModel>(
        R.layout.fragment_character
    ) {

    private val getImagesLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            try {
                uri.getByteArray(requireContext())?.let {
                    viewModel.onImageSelected(it)
                }
            } catch (e: NullPointerException) {
                showMessage("Sorry, something went wrong")
                e.printStackTrace()
            }
        }

    override val viewModel: CharacterViewModel by viewModel {
        val char = arguments?.getParcelable<Character>(CHAR_KEY)
        parametersOf(char)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Character>(CHAR_KEY).let { binding.character = it }

        binding.bgAddToFav.setOnClickListener {
            viewModel.onChangeFavoriteStatusPressed()
        }

        binding.bgRemoveFromFav.setOnClickListener {
            viewModel.onChangeFavoriteStatusPressed()
        }

        binding.ivBack.setOnClickListener {
            viewModel.onBackPressed()
        }

        binding.ivMain.setOnClickListener {
            getImagesLauncher.launch("image/*")
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