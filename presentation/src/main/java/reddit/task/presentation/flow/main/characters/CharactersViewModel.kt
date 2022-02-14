package reddit.task.presentation.flow.main.characters

import android.content.res.Resources
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import reddit.task.domain.usecases.CharactersUseCase
import reddit.task.presentation.R
import reddit.task.presentation.base.BaseViewModel
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class CharactersViewModel(
    private val loadInitialUseCase: CharactersUseCase.LoadInitial,
    private val fetchAllUseCase: CharactersUseCase.FetchAll,
) : BaseViewModel<CharactersState, CharactersEffect, CharactersEvent>() {

    override fun provideInitialState(): CharactersState = CharactersState()

    private var nextPage: String? = null

    init {
        loadInitialUseCase
            .execute(Unit)
            .onEach {
                emitState(state.value.copy(characters = it))
            }
            .launchFlow()
    }

    override fun onEvent(event: CharactersEvent) {
        when(event) {
            is CharactersEvent.LoadInitialPage -> loadPage(isInitial = true)
            is CharactersEvent.LoadNextPage -> loadPage(isInitial = false)
        }
    }

    override fun onError(context: CoroutineContext, throwable: Throwable) {
        emitEffect(CharactersEffect.NotifyError)
        Timber.e(throwable)
    }

    private fun loadPage(isInitial: Boolean) {
        if(!isInitial && nextPage == null) return
        emitEffect(CharactersEffect.NotifyLoading)
        fetchAllUseCase.use(CharactersUseCase.FetchAll.Params(nextPage)) {
            nextPage = it
        }
    }

}