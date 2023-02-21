package com.example.fzthtv.states

data class AuthState(
    var errorMessage: Boolean = false,
    var successMessage: Boolean = false,
    var email: String = "",
    var password: String = ""
)