package app.harry.udaexample.data

import app.harry.udaexample.domain.LoadState
import app.harry.udaexample.domain.repo.VerbRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import kotlin.random.Random
import kotlin.runCatching

class VerbRepositoryImp : VerbRepository {
    override fun getVerbs(): Flow<LoadState<List<String>>> {
        return flow {
            emit(LoadState.Loading())
            runCatching {
                withTimeout(1500) {
                    delay(Random.nextLong(500, 2000))
                    listOf("run", "swim", "fly")
                }
            }.onFailure {
                emit(LoadState.Failed(it))
            }.onSuccess {
                emit(LoadState.Succeed(it))
            }
        }
    }
}