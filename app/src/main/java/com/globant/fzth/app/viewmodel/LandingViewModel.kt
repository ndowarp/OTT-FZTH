package com.globant.fzth.app.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globant.fzth.app.events.LandingEvents
import com.globant.fzth.app.states.LandingState
import com.globant.fzth.domain.companions.Resource
import com.globant.fzth.domain.models.Movie
import com.globant.fzth.domain.usecases.GetMovies
import com.globant.fzth.domain.usecases.SearchMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val getMovies: GetMovies,
    private val searchMovies: SearchMovies
) : ViewModel() {
    private val _state = mutableStateOf(LandingState())
    val state: State<LandingState> = _state

    init {
        loadSection(gender = Movie.Companion.Genders.TRENDING)
        loadSection(gender = Movie.Companion.Genders.TOP_RATED)
        loadSection(gender = Movie.Companion.Genders.ACTION)
        loadSection(gender = Movie.Companion.Genders.COMEDY)
        loadSection(gender = Movie.Companion.Genders.HORROR)
        loadSection(gender = Movie.Companion.Genders.ROMANCE)
        loadSection(gender = Movie.Companion.Genders.DOCUMENTARIES)
    }

    fun onEvent(event: LandingEvents) {
        when (event) {
            is LandingEvents.OnError -> _state.value = state.value.copy(
                errorMessage = event.message
            )
            is LandingEvents.OnSectionLoaded -> {
                _state.value = state.value.copy(
                    isLoading = state.value.trendingMovies.isEmpty() &&
                            state.value.topRatedMovie.isEmpty() &&
                            state.value.actionMovies.isEmpty() &&
                            state.value.comedyMovies.isEmpty() &&
                            state.value.horrorMovies.isEmpty() &&
                            state.value.romanceMovies.isEmpty() &&
                            state.value.documentariesMovies.isEmpty()
                )
            }
            is LandingEvents.OnMovieSelected -> {
            }
            is LandingEvents.OnSearch -> {
                _state.value = state.value.copy(searchText = event.search)
                searchMovie(state.value.searchText)
            }
        }
    }

    private fun loadSection(gender: Movie.Companion.Genders) = viewModelScope.launch {
        getMovies(gender).collect() { result ->
            when (result) {
                is Resource.Error -> onEvent(LandingEvents.OnError(result.message!!))
                is Resource.Loading -> _state.value = state.value.copy(
                    isLoading = true
                )
                is Resource.Success -> {
                    when (gender) {
                        Movie.Companion.Genders.TRENDING -> _state.value = state.value.copy(
                            trendingMovies = result.data!!
                        )
                        Movie.Companion.Genders.TOP_RATED -> _state.value = state.value.copy(
                            topRatedMovie = result.data!!
                        )
                        Movie.Companion.Genders.ACTION -> _state.value = state.value.copy(
                            actionMovies = result.data!!
                        )
                        Movie.Companion.Genders.COMEDY -> _state.value = state.value.copy(
                            comedyMovies = result.data!!
                        )
                        Movie.Companion.Genders.HORROR -> _state.value = state.value.copy(
                            horrorMovies = result.data!!
                        )
                        Movie.Companion.Genders.ROMANCE -> _state.value = state.value.copy(
                            romanceMovies = result.data!!
                        )
                        Movie.Companion.Genders.DOCUMENTARIES -> _state.value = state.value.copy(
                            documentariesMovies = result.data!!
                        )
                    }
                    onEvent(LandingEvents.OnSectionLoaded(result.data!!, gender))
                }
            }
        }
    }

    private fun searchMovie(search: String) = viewModelScope.launch {
        searchMovies(search).collect() { result ->
            when (result) {
                is Resource.Error -> onEvent(LandingEvents.OnError(result.message!!))
                is Resource.Loading -> _state.value = state.value.copy(
                    isLoading = true
                )
                is Resource.Success -> {
                    _state.value = state.value.copy(searchResult = result.data!!)
                    onEvent(LandingEvents.OnSearch(search))
                }
            }
        }
    }

}
