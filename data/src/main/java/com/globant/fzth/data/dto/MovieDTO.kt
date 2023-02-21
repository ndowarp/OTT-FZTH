package com.globant.fzth.data.dto

import com.globant.fzth.data.apis.tmdb.MoviesConstants.TMDB_POSTER_URL
import com.globant.fzth.data.apis.tmdb.MoviesConstants.TMDB_THUMBNAIL_URL
import com.globant.fzth.data.entities.tmdb.Movie as Entity
import com.globant.fzth.domain.models.Movie as Domain

fun Entity.toDomain(): Domain {
    return Domain(
        title = title,
        adult = adult,
        backdropPath = backdropPath,
        mediaType = mediaType,
        releaseDate = releaseDate,
        firstAirDate = firstAirDate,
        genreIds = genreIds,
        name = name,
        originCountry = originCountry,
        id = id,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        voteAverage = voteAverage,
        voteCount = voteCount,
        thumbnailURL = "${TMDB_THUMBNAIL_URL}${if (backdropPath.isNullOrEmpty()) posterPath else backdropPath}",
        posterURL = "${TMDB_POSTER_URL}${if (backdropPath.isNullOrEmpty()) posterPath else backdropPath}"
    )
}
