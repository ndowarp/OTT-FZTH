package com.globant.fzth.data.entities.tmdb

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: Long,
    val results: List<Movie>,
    @SerializedName(value = "total_pages")
    val totalPage: Long,
    @SerializedName(value = "total_results")
    val totalResults: Long
)
