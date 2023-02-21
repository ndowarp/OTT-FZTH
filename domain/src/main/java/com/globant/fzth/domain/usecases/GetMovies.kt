package com.globant.fzth.domain.usecases

import com.globant.fzth.domain.companions.Resource
import com.globant.fzth.domain.models.Movie
import com.globant.fzth.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovies @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(gender: Movie.Companion.Genders): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getByGender(gender)
            emit(Resource.Success(response))
        } catch (ex: Exception) {
            emit(Resource.Error(message = ex.message!!, stacktrace = ex.stackTrace))
        }
    }
}
