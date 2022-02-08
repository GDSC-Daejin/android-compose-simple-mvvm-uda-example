package app.harry.udaexample.domain.usecase

import app.harry.udaexample.domain.LoadState
import app.harry.udaexample.domain.repo.NounRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRandomNounUseCase @Inject constructor(
    private val nounRepository: NounRepository
) {
    operator fun invoke() = nounRepository
        .getNouns()
        .map {
            when (it) {
                is LoadState.Failed -> LoadState.Failed(it.error)
                is LoadState.Loading -> LoadState.Loading()
                is LoadState.Idle -> LoadState.Idle()
                is LoadState.Succeed -> LoadState.Succeed(it.data.random())
            }
        }
}