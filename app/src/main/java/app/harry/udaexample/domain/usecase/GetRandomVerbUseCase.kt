package app.harry.udaexample.domain.usecase

import app.harry.udaexample.domain.LoadState
import app.harry.udaexample.domain.repo.VerbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRandomVerbUseCase @Inject constructor(
    private val verbRepository: VerbRepository
) {
    operator fun invoke(): Flow<LoadState<String>> {
        return verbRepository.getVerbs()
            .map {
                when (it) {
                    is LoadState.Failed -> LoadState.Failed(it.error)
                    is LoadState.Loading -> LoadState.Loading()
                    is LoadState.Idle -> LoadState.Idle()
                    is LoadState.Succeed -> LoadState.Succeed(it.data.random())
                }
            }
    }
}