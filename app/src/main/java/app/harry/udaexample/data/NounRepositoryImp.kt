package app.harry.udaexample.data

import app.harry.udaexample.domain.LoadState
import app.harry.udaexample.domain.repo.NounRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import kotlin.random.Random

class NounRepositoryImp : NounRepository {
    override fun getNouns(): Flow<LoadState<List<String>>> {
        return flow {
            emit(LoadState.Loading())
            runCatching {
                withTimeout(1500) {
                    delay(Random.nextLong(500, 2000))
                    listOf("cat", "dog", "bird", "fish")
                }
            }.onFailure {
                emit(LoadState.Failed(it))
            }.onSuccess {
                emit(LoadState.Succeed(it))
            }
        }
    }
}