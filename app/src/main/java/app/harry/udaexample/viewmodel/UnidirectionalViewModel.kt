package app.harry.udaexample.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

interface UnidirectionalViewModelDelegate<S: Any, EF, E> {

    fun Flow<S>.asStateFlow(
        defaultValue : S,
        coroutineScope : CoroutineScope
    ) : StateFlow<S> = stateIn(
        coroutineScope,
        SharingStarted.Lazily,
        defaultValue
    )

    val effect: Flow<EF>
    val state: StateFlow<S>
    fun event(e: E)

}

data class ViewModelComponent<S, EF, E> constructor(
    val state: S,
    val effect: Flow<EF>,
    val dispatch: (E) -> Unit
)

@Composable
fun <S : Any, EF, E> UnidirectionalViewModelDelegate<S, EF, E>.extract(): ViewModelComponent<S, EF, E> {

    val state by state.collectAsState()

    val dispatch: (E) -> Unit = { event -> event(event) }

    return ViewModelComponent(
        state = state,
        effect = effect,
        dispatch = dispatch
    )
}