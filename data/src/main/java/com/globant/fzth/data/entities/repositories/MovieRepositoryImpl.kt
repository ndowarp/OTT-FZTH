package com.globant.fzth.data.entities.repositories

import com.globant.fzth.data.apis.tmdb.MoviesAPI
import com.globant.fzth.data.dto.toDomain
import com.globant.fzth.domain.models.Movie
import com.globant.fzth.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val tmdbMoviesAPI: MoviesAPI
) : MovieRepository {
    override suspend fun getByGender(gender: Movie.Companion.Genders): List<Movie> {
        return when(gender){
            Movie.Companion.Genders.TRENDING -> tmdbMoviesAPI.getTrendingMovies().results.map { it.toDomain() }
            Movie.Companion.Genders.TOP_RATED -> tmdbMoviesAPI.getTopRatedMovies().results.map { it.toDomain() }
            Movie.Companion.Genders.ACTION -> tmdbMoviesAPI.getActionMovies().results.map { it.toDomain() }
            Movie.Companion.Genders.COMEDY -> tmdbMoviesAPI.getComedyMovies().results.map { it.toDomain() }
            Movie.Companion.Genders.HORROR ->  tmdbMoviesAPI.getHorrorMovies().results.map { it.toDomain() }
            Movie.Companion.Genders.ROMANCE -> tmdbMoviesAPI.getRomanceMovies().results.map { it.toDomain() }
            Movie.Companion.Genders.DOCUMENTARIES -> tmdbMoviesAPI.getDocumentaryMovies().results.map { it.toDomain() }
        }
    }

    override suspend fun getBySearch(search: String): List<Movie> {
        return tmdbMoviesAPI.getSearch(search).results.map { it.toDomain() }
    }
}