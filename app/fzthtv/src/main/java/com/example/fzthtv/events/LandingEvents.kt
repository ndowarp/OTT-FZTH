package com.example.fzthtv.events

import com.globant.fzth.domain.models.Movie

sealed class LandingEvents() {
    data class OnSectionLoaded(val movies: List<Movie>, val genders: Movie.Companion.Genders) :
        LandingEvents()
    data class OnError(val message: String) : LandingEvents()
    data class OnMovieSelected(val movie: Movie) : LandingEvents()
    data class OnSearch(val search: String) : LandingEvents()
}
