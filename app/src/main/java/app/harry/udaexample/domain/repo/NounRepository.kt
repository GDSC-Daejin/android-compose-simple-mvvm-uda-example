package app.harry.udaexample.domain.repo

import app.harry.udaexample.domain.LoadState
import kotlinx.coroutines.flow.Flow

interface NounRepository {
    fun getNouns(): Flow<LoadState<List<String>>>
}