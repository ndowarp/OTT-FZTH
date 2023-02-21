package com.globant.fzth.data.dto

import com.globant.fzth.data.entities.payment.CreditCard as Entity
import com.globant.fzth.domain.models.CreditCard as Domain

fun Entity.toDomain(): Domain {
    return Domain(
        cardNumber = cardNumber,
        ownerName = ownerName,
        expirationDate = expirationDate,
        securityCode = securityCode,
    )
}
