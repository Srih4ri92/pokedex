package com.sri.pokedex.fearure.home

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sri.pokedex.core.data.repository.home.HomeRepository
import com.sri.pokedex.core.model.Pokemon
import com.sri.pokedex.core.viewmodel.BaseViewModel
import com.sri.pokedex.core.viewmodel.ViewModelStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): BaseViewModel() {

    internal val uiState: ViewModelStateFlow<HomeUiState> = viewModelStateFlow(HomeUiState.Loading)

    private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    val pokemonList:StateFlow<List<Pokemon>> = pokemonFetchingIndex.flatMapLatest {
            homeRepository.fetchPokemonList(
                page = pokemonFetchingIndex.value,
                onStart = {uiState.tryEmit(key,HomeUiState.Loading)},
                onComplete = {uiState.tryEmit(key, HomeUiState.Idle)},
                onError = {error->
                    uiState.tryEmit(key,HomeUiState.Error(error))
                }
            )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun fetchNextPokemonList(){
        pokemonFetchingIndex.value++
    }

}

@Stable
internal sealed interface HomeUiState {

    data object Loading: HomeUiState

    data object Idle: HomeUiState

    data class Error(val message: String?): HomeUiState
}