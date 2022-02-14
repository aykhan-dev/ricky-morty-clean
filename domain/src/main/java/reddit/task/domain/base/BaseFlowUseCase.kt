package reddit.task.domain.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseFlowUseCase<Params, Result> {
    protected abstract fun createFlow(params: Params): Flow<Result>

    fun execute(params: Params): Flow<Result> = createFlow(params).flowOn(Dispatchers.IO)
}