package reddit.task.presentation.flow.main.characters

import reddit.task.domain.model.Character

/*
    For each screen, only one data class is used as it be in jetpack compose way development;
    View models can emit states by help of 'copy()' function of data classes and change only needed fields;
 */

data class CharactersState(val characters: List<Character>? = null)

sealed class CharactersEffect {
    object NotifyLoading: CharactersEffect()
    object NotifyError: CharactersEffect()
}

/*
    Events are triggers for actions by view side;
 */

sealed class CharactersEvent {
    object LoadInitialPage: CharactersEvent()
    object LoadNextPage: CharactersEvent()
}