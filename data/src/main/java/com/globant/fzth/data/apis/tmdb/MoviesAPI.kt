package com.globant.fzth.data.apis.tmdb

import com.globant.fzth.data.entities.tmdb.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {
    @GET(value = MoviesConstants.TMDB_TRENDING)
    suspend fun getTrendingMovies(): MovieResponse

    @GET(value = MoviesConstants.TMDB_TOP_RATED)
    suspend fun getTopRatedMovies(): MovieResponse

    @GET(value = MoviesConstants.TMDB_ROMANCE)
    suspend fun getRomanceMovies(): MovieResponse

    @GET(value = MoviesConstants.TMDB_ACTION)
    suspend fun getActionMovies(): MovieResponse

    @GET(value = MoviesConstants.TMDB_DOCUMENTARIES)
    suspend fun getDocumentaryMovies(): MovieResponse

    @GET(value = MoviesConstants.TMDB_HORROR)
    suspend fun getHorrorMovies(): MovieResponse

    @GET(value = MoviesConstants.TMDB_COMEDY)
    suspend fun getComedyMovies(): MovieResponse

    @GET(value = MoviesConstants.TMDB_SEARCH)
    suspend fun getSearch(@Query("query") search: String): MovieResponse
}
