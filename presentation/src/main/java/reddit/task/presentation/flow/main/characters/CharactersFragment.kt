package reddit.task.presentation.flow.main.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import reddit.task.presentation.R
import reddit.task.presentation.base.BaseFragment
import reddit.task.presentation.databinding.FragmentCharactersBinding
import reddit.task.presentation.extensions.onScrollEnded
import reddit.task.presentation.extensions.showToast
import reddit.task.presentation.flow.main.characters.adapter.CharactersListAdapter
import kotlin.reflect.KClass

class CharactersFragment :
    BaseFragment<CharactersState, CharactersEffect, CharactersEvent, FragmentCharactersBinding, CharactersViewModel>() {

    override val screenName: String = CharactersFragment::class.simpleName ?: ""
    override val vmClazz: KClass<CharactersViewModel> = CharactersViewModel::class

    override val bindingCallback: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharactersBinding =
        FragmentCharactersBinding::inflate

    private val charactersListAdapter by inject<CharactersListAdapter>()

    override val onBind: FragmentCharactersBinding.() -> Unit = {
        rvCharacters.adapter = charactersListAdapter
        rvCharacters.onScrollEnded {
            viewModel.onEvent(CharactersEvent.LoadNextPage)
        }

        onEvent(CharactersEvent.LoadInitialPage)
    }

    override fun onStateChanged(state: CharactersState) {
        when {
            !state.characters.isNullOrEmpty() -> charactersListAdapter.submitList(state.characters)
        }
    }

    override fun onAffected(effect: CharactersEffect) {
        when(effect) {
            is CharactersEffect.NotifyLoading -> showToast(getString(R.string.loading))
            is CharactersEffect.NotifyError -> showToast(getString(R.string.something_went_wrong))
        }
    }

}