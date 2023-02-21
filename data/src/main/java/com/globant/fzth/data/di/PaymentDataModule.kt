package com.globant.fzth.data.di

import com.globant.fzth.data.apis.payment.PaymentAPI
import com.globant.fzth.data.apis.payment.PaymentConstants
import com.globant.fzth.data.entities.repositories.PaymentRepositoryImpl
import com.globant.fzth.domain.repositories.PaymentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PaymentDataModule {
    @Provides
    @Singleton
    fun providePaymentApi(): PaymentAPI {
        return Retrofit.Builder().baseUrl(PaymentConstants.PAYMENT_VALIDATION_BASE)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(PaymentAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(repository: PaymentAPI): PaymentRepository{
        return PaymentRepositoryImpl(repository)
    }
}
