package app.harry.udaexample.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import app.harry.udaexample.viewmodel.extract
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun HomeScreen(
    vm: HomeScreenViewModelDelegate = hiltViewModel<HomeScreenViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val context = LocalContext.current

    LaunchedEffect(true) {
        effect.onEach {
            when (it) {
                is HomeScreenViewModelDelegate.Effect.ShowError -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }.launchIn(this)
    }

    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                state.data?.let {
                    Text(it)
                }

                when {
                    state.isError -> ErrorRetryButton { event(HomeScreenViewModelDelegate.Event.PressRetryButton) }
                    state.isLoading -> CircularProgressIndicator()
                    else -> {
                        Button(onClick = { event(HomeScreenViewModelDelegate.Event.PressButton) }) {
                            Text("Press me")
                        }
                    }
                }

            }
        }
    }

}

@Composable
fun ErrorRetryButton(
    onRetry: () -> Unit
) {

    Button(
        onRetry,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error)
    ) { Text("Retry") }

}