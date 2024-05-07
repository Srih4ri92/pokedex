package com.sri.pokedex.feature.detail


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sri.pokedex.core.data.repository.detail.DetailRepository
import com.sri.pokedex.core.model.Pokemon
import com.sri.pokedex.core.model.PokemonInfo
import com.sri.pokedex.core.viewmodel.BaseViewModel
import com.sri.pokedex.core.viewmodel.ViewModelStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailRepository: DetailRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    internal val uiState: ViewModelStateFlow<DetailsUiState> =
        viewModelStateFlow(DetailsUiState.Loading)

    internal val pokemon = savedStateHandle.getStateFlow<Pokemon?>("pokemon", null)

    val pokemonInfo: StateFlow<PokemonInfo?> =
        pokemon.filterNotNull().flatMapLatest { pokemon ->
            detailRepository.fetchPokemonInfo(
                name = pokemon.name.replaceFirstChar { it.lowercase() },
                onComplete = { uiState.tryEmit(key, DetailsUiState.Idle) },
                onError = { uiState.tryEmit(key, DetailsUiState.Error(it)) }
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = null
        )

}

sealed class DetailsUiState {
    object Loading : DetailsUiState()
    object Idle : DetailsUiState()
    data class Error(val message: String?) : DetailsUiState()
}
