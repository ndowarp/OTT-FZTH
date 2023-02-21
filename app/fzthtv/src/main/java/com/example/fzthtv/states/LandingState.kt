package com.example.fzthtv.states

import com.globant.fzth.domain.models.Movie

data class LandingState(
    var isLoading: Boolean = true,
    var movieSelected: Movie? = null,
    val errorMessage: String? = null,
    var trendingMovies: List<Movie> = emptyList(),
    var topRatedMovie: List<Movie> = emptyList(),
    val actionMovies: List<Movie> = emptyList(),
    val comedyMovies: List<Movie> = emptyList(),
    val horrorMovies: List<Movie> = emptyList(),
    val romanceMovies: List<Movie> = emptyList(),
    val documentariesMovies: List<Movie> = emptyList(),
    var searchText: String = "",
    var searchResult: List<Movie> = emptyList()
    )
