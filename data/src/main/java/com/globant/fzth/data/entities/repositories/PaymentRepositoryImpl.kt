package com.globant.fzth.data.entities.repositories

import com.globant.fzth.data.apis.payment.PaymentAPI
import com.globant.fzth.data.dto.toDomain
import com.globant.fzth.domain.models.CreditCard
import com.globant.fzth.domain.repositories.PaymentRepository
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val paymentAPI : PaymentAPI
) : PaymentRepository {
    override suspend fun validatePayment(creditCard: CreditCard): CreditCard {
        return paymentAPI.validatePayment().results.toDomain()
    }
}