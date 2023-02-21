package com.globant.fzth.domain.models

data class CreditCard(
    var cardNumber: String,
    var ownerName: String,
    var expirationDate: String,
    var securityCode: String,
)
