package app.harry.udaexample.domain.repo

import app.harry.udaexample.domain.LoadState
import kotlinx.coroutines.flow.Flow

interface VerbRepository {
    fun getVerbs(): Flow<LoadState<List<String>>>
}