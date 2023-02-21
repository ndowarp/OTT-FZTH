package com.globant.fzth.domain.models

import java.util.Date

data class Movie(
    val title: String?,
    val adult: Boolean?,
    val backdropPath: String?,
    val mediaType: String?,
    val releaseDate: Date?,
    val firstAirDate: Date?,
    val genreIds: List<Long>,
    val name: String?,
    val originCountry: List<String>?,
    val id: Long,
    val originalLanguage: String,
    val originalName: String?,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val voteAverage: Float,
    val voteCount: Long,
    val thumbnailURL: String,
    val posterURL: String
) {
    companion object {
        enum class Genders() {
            TRENDING,
            TOP_RATED,
            ACTION,
            COMEDY,
            HORROR,
            ROMANCE,
            DOCUMENTARIES
        }
    }

    fun getMovieTitle(): String {
        return if (this.name.isNullOrEmpty()) this.title!! else this.name!!
    }
}
