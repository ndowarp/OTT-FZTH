package com.globant.fzth.data.apis.tmdb

object MoviesConstants {
    private const val TMDB_API_KEY = "ee89e5a0131dfd969cf11682f97805e0"
    const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    const val TMDB_THUMBNAIL_URL = "https://image.tmdb.org/t/p/w500"
    const val TMDB_POSTER_URL = "https://image.tmdb.org/t/p/original/"

    const val TMDB_TRENDING =
        "trending/all/week?api_key=$TMDB_API_KEY&language=en-US"

    const val TMDB_ACTION =
        "discover/movie?api_key=$TMDB_API_KEY&language=en-US&with_genres=28"

    const val TMDB_TOP_RATED =
        "movie/top_rated?api_key=$TMDB_API_KEY&language=en-US"

    const val TMDB_COMEDY =
        "discover/movie?api_key=$TMDB_API_KEY&language=en-US&with_genres=35"

    const val TMDB_HORROR =
        "discover/movie?api_key=$TMDB_API_KEY&language=en-US&with_genres=27"

    const val TMDB_ROMANCE =
        "discover/movie?api_key=$TMDB_API_KEY&language=en-US&with_genres=10749"

    const val TMDB_DOCUMENTARIES =
        "discover/movie?api_key=$TMDB_API_KEY&language=en-US&with_genres=99"

    const val TMDB_SEARCH =
        "search/multi?api_key=$TMDB_API_KEY&language=en-US&page=1"

}