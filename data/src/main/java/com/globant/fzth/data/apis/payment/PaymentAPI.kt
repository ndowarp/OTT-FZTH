package com.globant.fzth.data.apis.payment

import com.globant.fzth.data.entities.payment.CreditCardResponse
import retrofit2.http.POST

interface PaymentAPI {

    @POST(value = PaymentConstants.PAYMENT_VALIDATION)
    suspend fun validatePayment(): CreditCardResponse

}
