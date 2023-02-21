package com.globant.fzth.domain.repositories

import com.globant.fzth.domain.models.Movie

interface MovieRepository {
    suspend fun getByGender(gender: Movie.Companion.Genders): List<Movie>
    suspend fun getBySearch(search: String): List<Movie>
}
