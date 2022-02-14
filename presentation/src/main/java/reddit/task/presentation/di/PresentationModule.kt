package reddit.task.presentation.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import reddit.task.presentation.flow.main.characters.CharactersViewModel
import reddit.task.presentation.flow.main.characters.adapter.CharactersListAdapter

val presentationModule = module {

    factory {
        CharactersListAdapter()
    }

    viewModel {
        CharactersViewModel(
            loadInitialUseCase = get(),
            fetchAllUseCase = get(),
        )
    }

}