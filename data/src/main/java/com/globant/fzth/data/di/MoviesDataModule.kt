package com.globant.fzth.data.di

import com.globant.fzth.data.apis.tmdb.MoviesConstants
import com.globant.fzth.data.apis.tmdb.MoviesAPI
import com.globant.fzth.data.entities.repositories.MovieRepositoryImpl
import com.globant.fzth.domain.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesDataModule {
    @Provides
    @Singleton
    fun provideTMDBApi(): MoviesAPI {
        return Retrofit.Builder().baseUrl(MoviesConstants.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MoviesAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(repository: MoviesAPI): MovieRepository{
        return MovieRepositoryImpl(repository)
    }
}
