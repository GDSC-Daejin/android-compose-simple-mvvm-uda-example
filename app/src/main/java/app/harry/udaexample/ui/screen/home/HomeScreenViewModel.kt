package app.harry.udaexample.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.harry.udaexample.domain.LoadState
import app.harry.udaexample.domain.usecase.GetRandomNounUseCase
import app.harry.udaexample.domain.usecase.GetRandomVerbUseCase
import app.harry.udaexample.viewmodel.UnidirectionalViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

interface HomeScreenViewModelDelegate :
    UnidirectionalViewModelDelegate<
            HomeScreenViewModelDelegate.State,
            HomeScreenViewModelDelegate.Effect,
            HomeScreenViewModelDelegate.Event> {

    data class State(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val data: String? = null
    )

    interface Effect {
        class ShowError(val message: String) : Effect
    }

    interface Event {
        object PressButton : Event
        object PressRetryButton : Event
    }

}

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getRandomNounUseCase: GetRandomNounUseCase,
    private val getRandomVerbUseCase: GetRandomVerbUseCase
) : ViewModel(), HomeScreenViewModelDelegate {

    private val effectChannel = Channel<HomeScreenViewModelDelegate.Effect>(Channel.BUFFERED)

    override val effect: Flow<HomeScreenViewModelDelegate.Effect> = effectChannel.receiveAsFlow()

    private val randomNoun = MutableStateFlow<LoadState<String>>(LoadState.Idle())
    private val randomVerb = MutableStateFlow<LoadState<String>>(LoadState.Idle())

    override val state = combine(
        randomNoun,
        randomVerb
    ) { nounState, verbState ->

        val data = if (nounState is LoadState.Succeed && verbState is LoadState.Succeed) {
            "${nounState.data} ${verbState.data}"
        } else {
            null
        }

        val isError = nounState.isFailed || verbState.isFailed

        if (isError) {
            effectChannel.send(HomeScreenViewModelDelegate.Effect.ShowError("Error"))
        }

        HomeScreenViewModelDelegate.State(
            isLoading = nounState.isLoading || verbState.isLoading,
            isError = isError,
            data = data
        )
    }.asStateFlow(HomeScreenViewModelDelegate.State(), viewModelScope)


    private fun load() {
        getRandomNounUseCase()
            .onEach { randomNoun.emit(it) }
            .launchIn(viewModelScope)

        getRandomVerbUseCase()
            .onEach { randomVerb.emit(it) }
            .launchIn(viewModelScope)
    }

    override fun event(e: HomeScreenViewModelDelegate.Event) {
        when (e) {
            HomeScreenViewModelDelegate.Event.PressButton -> load()
            HomeScreenViewModelDelegate.Event.PressRetryButton -> load()
        }
    }
}