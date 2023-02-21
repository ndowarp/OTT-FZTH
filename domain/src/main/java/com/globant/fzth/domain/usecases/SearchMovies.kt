package com.globant.fzth.domain.usecases

import com.globant.fzth.domain.companions.Resource
import com.globant.fzth.domain.models.Movie
import com.globant.fzth.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMovies @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(search : String): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getBySearch(search)
            emit(Resource.Success(response))
        } catch (ex: Exception) {
            emit(Resource.Error(message = ex.message!!, stacktrace = ex.stackTrace))
        }
    }
}
