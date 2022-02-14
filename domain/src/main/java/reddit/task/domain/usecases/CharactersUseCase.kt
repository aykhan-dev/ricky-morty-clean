package reddit.task.domain.usecases

import reddit.task.domain.base.BaseFlowUseCase
import reddit.task.domain.base.BaseUseCase
import reddit.task.domain.model.Character
import reddit.task.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

class CharactersUseCase {

    class LoadInitial(
        private val charactersRepository: CharactersRepository
    ): BaseFlowUseCase<Unit, List<Character>>() {

        override fun createFlow(params: Unit): Flow<List<Character>> {
            return charactersRepository.getAll()
        }

    }

    class FetchAll(
        private val charactersRepository: CharactersRepository
    ): BaseUseCase<FetchAll.Params, String?>() {

        data class Params(val page: String? = null)

        override suspend fun execute(params: Params): String? {
            return charactersRepository.fetch(params.page)
        }

    }

}