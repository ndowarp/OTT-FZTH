package com.globant.fzth.app.states

import com.globant.fzth.domain.models.CreditCard

data class PaymentState(
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var successMessage: Boolean = false,
    var creditCard: CreditCard? = CreditCard("","","","")
)