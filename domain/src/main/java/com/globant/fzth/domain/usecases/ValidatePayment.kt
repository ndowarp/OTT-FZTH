package com.globant.fzth.domain.usecases

import com.globant.fzth.domain.companions.Resource
import com.globant.fzth.domain.models.CreditCard
import com.globant.fzth.domain.repositories.PaymentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidatePayment @Inject constructor(
    private val repository: PaymentRepository
) {
    suspend operator fun invoke(creditCard: CreditCard): Flow<Resource<CreditCard>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.validatePayment(creditCard)
            emit(Resource.Success(response))
        } catch (ex: Exception) {
            emit(Resource.Error(message = ex.message!!, stacktrace = ex.stackTrace))
        }
    }
}
