package com.globant.fzth.domain.repositories

import com.globant.fzth.domain.models.CreditCard

interface PaymentRepository {

    suspend fun validatePayment(creditCard: CreditCard): CreditCard
}