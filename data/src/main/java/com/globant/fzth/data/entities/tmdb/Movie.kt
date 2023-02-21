package com.globant.fzth.data.entities.tmdb

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Movie(
    val title: String?,
    val adult: Boolean?,
    @SerializedName(value = "backdrop_path")
    val backdropPath: String?,
    @SerializedName(value = "media_type")
    val mediaType: String?,
    @SerializedName(value = "release_date")
    val releaseDate: Date?,
    @SerializedName(value = "first_air_date")
    val firstAirDate: Date?,
    @SerializedName(value = "genre_ids")
    val genreIds: List<Long>,
    val name: String?,
    @SerializedName(value = "origin_country")
    val originCountry: List<String>?,
    val id: Long,
    @SerializedName(value = "original_language")
    val originalLanguage: String,
    @SerializedName(value = "original_name")
    val originalName: String?,
    val overview: String,
    val popularity: Float,
    @SerializedName(value = "poster_path")
    val posterPath: String,
    @SerializedName(value = "vote_average")
    val voteAverage: Float,
    @SerializedName(value = "vote_count")
    val voteCount: Long
)
