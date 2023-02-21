package com.globant.fzth.data.entities.payment

data class CreditCard(
    val cardNumber: String,
    val ownerName: String,
    val expirationDate: String,
    val securityCode: String,
)
