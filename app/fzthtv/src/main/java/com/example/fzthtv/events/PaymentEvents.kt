package com.example.fzthtv.events

import com.globant.fzth.domain.models.CreditCard

sealed class PaymentEvents(){

    data class ValidatePayment(val payment: CreditCard) : PaymentEvents()
    data class OnError(val message: String) : PaymentEvents()
}
