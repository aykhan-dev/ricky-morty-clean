package reddit.task.domain.di

import reddit.task.domain.usecases.CharactersUseCase
import org.koin.dsl.module

val domainModule = module {

    single {
        CharactersUseCase.LoadInitial(
            charactersRepository = get(),
        )
    }

    single {
        CharactersUseCase.FetchAll(
            charactersRepository = get()
        )
    }

}