package app.harry.udaexample.domain

sealed interface LoadState<T> {

    val isLoading get() = this is Loading
    val isFailed get() = this is Failed

    class Idle<T> : LoadState<T>

    class Loading<T> : LoadState<T>

    data class Succeed<T>(val data : T) : LoadState<T>

    data class Failed<T>(val error : Throwable) : LoadState<T>

}